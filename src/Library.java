import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.io.Serializable;

//clear && javac *.java && java Application

public class Library implements Serializable {
    private ArrayList<Borrower> borrowers;
    private ArrayList<Librarian> librarians;
    private transient Scanner input;
    private transient Person currLogin;
    private Catalog catalog;
    
    private static final String ADMIN_USERNAME = "Zues";
    private static final String ADMIN_PASSWORD = "qwerty";

    public Library() {
        borrowers = new ArrayList<>();
        librarians = new ArrayList<>();
        input = new Scanner(System.in);
        catalog = new Catalog();
        currLogin = null;
    }

    public void saveLibrary() {
        try (ObjectOutputStream out =
            new ObjectOutputStream(
                new FileOutputStream("/workspaces/LibraryManagementSystem/data/library.dat"))) {

            out.writeObject(this);
            System.out.println("Library saved.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Library loadLibrary() {
        try (ObjectInputStream in =
            new ObjectInputStream(
                new FileInputStream("/workspaces/LibraryManagementSystem/data/library.dat"))) {

            Library lib = (Library) in.readObject();

            lib.input = new Scanner(System.in);
            lib.currLogin = null;

            System.out.println("Library loaded.");
            return lib;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved library found. Creating new one.");
            return new Library();
        }
    }

    public void search(int type) {
        System.out.print("Search string: " );
        String searchStr = input.nextLine();

        ArrayList<Book> results = new ArrayList<>();
        switch(type) {
            case 1: {
                results = catalog.searchBooks(searchStr, "title");
                break;
            }
            case 2: {
                results = catalog.searchBooks(searchStr, "author");
                break;
            }
            case 3: {
                results = catalog.searchBooks(searchStr, "isbn");
                break;
            }
        }
    
        Catalog.displayResults(results);

        if(currLogin instanceof Borrower borrower) {
            while(results.size() > 0) {
                System.out.print("\nEnter book number to check out or request if not avalable (0 to skip): ");
                int bookNum = input.nextInt();

                if(bookNum == 0) {
                    break;
                }

                Book book = results.get(bookNum-1);
                if(!borrower.hasBook(book)) {
                    if(book.getStatus() == Status.AVAILABLE) {
                        
                            borrower.checkOutBook(book);
                        
                        System.out.println(book.getTitle() + " is now checked out!");
                    }
                    else {
                        borrower.addHoldRequest(book);
                        System.out.println(book.getTitle() + " has been requested.");
                    }
                }
                else {
                    System.out.println("Book already in account");
                }
            }
        }
        
        

        System.out.println();
    }

    public void addBorrower() {
        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter address: ");
        String address = input.nextLine();

        System.out.print("Enter email: ");
        String email = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        borrowers.add(new Borrower(name, address, email, password));
        System.out.println("Welcome " + name + "!");

        currLogin = borrowers.getLast();
    }

    public void addLibrarian() {
        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter address: ");
        String address = input.nextLine();

        System.out.print("Enter email: ");
        String email = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter yearly salary: ");
        String salary = input.nextLine();

        librarians.add(new Librarian(name, address, email, password, salary));
        System.out.println("Welcome " + name + "!");
    }

    public void login() {
        System.out.println("------ Account Login ------");

        while(true) {
            System.out.print("Email: ");
            String email = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            currLogin = null;
            
            for(Borrower b: borrowers) {
                if(email.equals(b.getEmail()) && password.equals(b.getPassword())) {
                    currLogin = b;
                }
            }
            
            if(currLogin == null) {
                for(Librarian l: librarians) {
                    if(email.equals(l.getEmail()) && password.equals(l.getPassword())) {
                        currLogin = l;
                    }
                }
            }

            if(currLogin == null) {
                System.out.println("Email or Password incorrect");
            }
            else {
                break;
            }
        }
    }

    public void adminPortal() {
        System.out.println("------ Admin Login ------");
        System.out.print("Enter Admin username: ");
        String username = input.nextLine();

        System.out.print("Enter Admin password: ");
        String password = input.nextLine();

        if(username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("-------------------------------------");
            System.out.println("Welcome to the Administrative Portal!");

            boolean running = true;
            while(running) {
                System.out.println("\n1. Add new librarian");
                System.out.println("2. See all borrowers");
                System.out.println("3. Return to menu");
                
                System.out.print("Choice > ");
                int choice = input.nextInt();

                switch(choice) {
                    case 1:
                        addLibrarian();
                        break;
                    case 2: {
                        for(Borrower b: borrowers) {
                            System.out.println(b);
                        }
                        break;
                    }
                    case 3: 
                        running = false;
                        break;
                }
            }
        }
    }

    public void updateHolds(Book book) {
        for(Borrower b : borrowers) {
            for(HoldRequest h : b.getHolds()) {
                if(h.getBook().equals(book)) {
                    h.setReady();
                }
            }
        }
    }

    public void checkAccount() {
        if(currLogin instanceof Borrower borrower) {
            System.out.println("------ Your Account ------");
            borrower.printBooks();

            ArrayList<Integer> checkIns = new ArrayList<>();
            while(borrower.getCheckedOut().size() > 0) {
                System.out.print("\nEnter book number to return (0 to skip): ");
                int bookNum = input.nextInt();
                if(bookNum == 0) {
                    break;
                }
                else {
                    checkIns.add(bookNum-1);
                }
            }
            checkIns.sort( (a, b) -> { return a.compareTo(b); } );
            for(Integer i : checkIns) {
                System.out.println(i);
                Loan loan = borrower.getCheckedOut().get(i);
                updateHolds(loan.getBook());
                borrower.checkInBook(loan);
            }

            for(HoldRequest hold : borrower.getReadyBooks()) {
                System.out.println("You have a book ready! Press enter to check out " + hold.getBook().getTitle() + ": ");
                input.nextLine();
                borrower.checkOutHoldBook(hold);
            }

            System.out.print("Press enter to return");
            input.nextLine();
        }
    }

    public void addNewBook() {
        System.out.println("Enter book title: ");
        String title = input.nextLine();

        System.out.println("Enter book author: ");
        String author = input.nextLine();

        System.out.println("Enter book isbn: ");
        String isbn = input.nextLine();

        System.out.println("Enter book subject: ");
        String subject = input.nextLine();

        catalog.addBook(title, author, isbn, subject);
    }

    public void run() {
        boolean running = true;
        while(running) {
            System.out.println("\nOptions:");
            System.out.println("1. Search Books");
            if(currLogin == null) {
                System.out.println("2. Log in");
                System.out.println("3. New Borrower account:");
                System.out.println("4. Administrative portal");
                System.out.println("5. Quit");
            }
            else if(currLogin instanceof Borrower) {
                System.out.println("2. Check account");
                System.out.println("3. Log Out");
            }
            else if(currLogin instanceof Librarian) {
                System.out.println("2. Add new Book");
                System.out.println("3. See all books");
                System.out.println("4. Log Out");
            }
            System.out.print("Choice > ");

            int choice = input.nextInt();
            input.nextLine();

            Application.clear();

            switch(choice) {
                case 1: {
                    while(true) {
                        System.out.println("------ Search Items ------");
                        System.out.println("Search by:");
                        System.out.println("1. Book title");
                        System.out.println("2. Book author");
                        System.out.println("3. ISBN");
                        System.out.println("4. Quit to main");
                        System.out.print("Choice > ");

                        int type = input.nextInt();
                        input.nextLine();

                        if(type == 4) {
                            Application.clear();
                            break;
                        }

                        search(type);
                    }
                    break;
                }
                case 2: {
                    if(currLogin == null) {
                        login();
                    }
                    else if(currLogin instanceof Borrower) {
                        checkAccount();
                    }
                    else {
                        addNewBook();
                    }
                    Application.clear();
                    break;
                }
                case 3: {
                    if(currLogin == null) {
                        System.out.println("------ Borrower Sign up ------");
                        addBorrower();
                    }
                    else if(currLogin instanceof Borrower){
                        currLogin = null;
                    }
                    else {
                        Catalog.displayResults(catalog.getAllBooks());
                    }
                    break;
                }
                case 4: {
                    if(currLogin instanceof Librarian) {
                        currLogin = null;
                    }
                    else {
                        adminPortal();
                    }
                    break;
                }
                case 5: {
                    running = false;
                    break;
                }
            }
            
        }
    }
}