import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

//clear && javac *.java && java Application

public class Library {
    private ArrayList<Borrower> borrowers;
    private ArrayList<Librarian> librarians;
    private Scanner input;
    private Catalog catalog;
    private static final String ADMIN_USERNAME = "Zues";
    private static final String ADMIN_PASSWORD = "qwerty";

    public Library() {
        borrowers = new ArrayList<>();
        librarians = new ArrayList<>();
        input = new Scanner(System.in);
        catalog = new Catalog();

        loadObjects();
    }

    public void loadObjects() {
        System.out.println("Loading objects...");
        try {
            FileInputStream file = new FileInputStream("/workspaces/LibraryManagementSystem/data/borrowers.txt");
            ObjectInputStream reader = new ObjectInputStream(file);

            while (true) {
                try {
                    Borrower b = (Borrower) reader.readObject();
                    borrowers.add(b);  
                } 
                catch (EOFException e) {
                    break;
                }
            }
            for(Borrower b: borrowers) {
                System.out.println(b);
            }

            System.out.println("Borrowers loaded");

            reader.close();
        }
        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("\n");
    }

    public void saveObjects() {
        try {
            FileOutputStream file = new FileOutputStream("/workspaces/LibraryManagementSystem/data/borrowers.txt");
            ObjectOutputStream writer = new ObjectOutputStream(file);

            for(Borrower b: borrowers) {
                writer.writeObject(b);
            }
            System.out.println("Borrowers saved");

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
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

        borrowers.add(new Borrower("1", name, address, email, password));
        System.out.println("Welcome " + name + "!");
    }

    public void addLibrarian() {
        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter address: ");
        String address = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter yearly salary: ");
        String salary = input.nextLine();

        librarians.add(new Librarian("1", name, address, username, password, salary));
        System.out.println("Welcome " + name + "!");
    }

    public void borrowerLogin() {
        System.out.println("------ Account Login ------");

        while(true) {
            System.out.print("Email: ");
            String email = input.nextLine();

            System.out.print("Password: ");
            String password = input.nextLine();

            Borrower currBorrower = null;
            for(Borrower b: borrowers) {
                if(email.equals(b.getEmail()) && password.equals(b.getPassword())) {
                    currBorrower = b;
                }
            }

            if(currBorrower == null) {
                System.out.println("Email or Password incorrect");
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



    public void run() {
        boolean running = true;
        while(running) {
            System.out.println("Options:");
            System.out.println("1. Search Books");
            System.out.println("2. Log in");
            System.out.println("3. New Borrower account:");
            System.out.println("4. Administrative portal");
            System.out.println("5. Quit");
            
            System.out.print("Choice > ");

            int choice = input.nextInt();
            input.nextLine();

            Application.clear();

            boolean running2 = true;
            while(running2) {
                switch(choice) {
                    case 1: {
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
                            running2 = false;
                            break;
                        }

                        search(type);

                        break;
                    }
                    case 2: {
                        borrowerLogin();
                        break;
                    }
                    case 3: {
                        System.out.println("------ Borrower Sign up ------");
                        addBorrower();
                        running2 = false;
                        break;
                    }
                    case 4: {
                        adminPortal();
                        running2 = false;
                        break;
                    }
                    case 5: {
                        saveObjects();
                        running = false;
                        running2 = false;
                        break;
                    }
                }
            }
        }
    }
}