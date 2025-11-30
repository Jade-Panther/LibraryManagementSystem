import java.util.Scanner;
import java.util.ArrayList;

//clear && javac *.java && java Application

public class Library {
    private ArrayList<Borrower> borrowers;
    private ArrayList<Librarian> librarians;
    private ArrayList<Book> items;
    private Scanner input;
    private Catalog catalog;

    public Library() {
        borrowers = new ArrayList<>();
        librarians = new ArrayList<>();
        items = new ArrayList<>();
        input = new Scanner(System.in);
        catalog = new Catalog();
    }

    public void addBorrower() {
        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter address: ");
        String address = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        borrowers.add(new Borrower("1", name, address, username, password));
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

        librarians.add(new Librarian("1", name, address, username, password));
    }

    public void run() {
        System.out.println("Options:");
        System.out.println("1. Search Books");
        System.out.println("2. Log in");
        System.out.println("3. New Borrower account:");
        System.out.println("4. Administrative portal");
        
        System.out.print("Choice > ");

        int choice = input.nextInt();
        input.nextLine();

        System.out.println();

        boolean running2 = true;
        while(running2) {
            switch(choice) {
                case 1: {
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
                    break;
                }
                case 3: {
                    addBorrower();
                    break;
                }
            }
        }
    }
}