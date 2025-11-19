import java.util.Scanner;
import java.util.ArrayList;

public class Library {
    private ArrayList<Borrower> borrowers;
    private ArrayList<Librarian> librarians;
    private ArrayList<Book> items;
    private Scanner input;

    public Library() {
        borrowers = new ArrayList<>();
        librarians = new ArrayList<>();
        items = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public void addBook() {

    }
    public void deleteBook() {

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
        System.out.println("1. New Borrower account:");
        System.out.println("3. Log in");
        System.out.println("4. Administrative portal");
        System.out.println("5. Search Books");
        System.out.print("Choice > ");

        int choice = input.nextInt();
        input.nextLine();

        System.out.println();

        switch(choice) {
            case 1:
                addBorrower();
                break;
        }

    }
}