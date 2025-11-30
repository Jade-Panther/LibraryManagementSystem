import java.util.ArrayList;
import java.util.Arrays;

public class Catalog {
    private ArrayList<Book> books = new ArrayList<>(Arrays.asList(
        new Book("To Kill a Mockingbird", "Harper Lee", "9780061120084", "Fiction"),
        new Book("1984", "George Orwell", "9780451524935", "Dystopian"),
        new Book("The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", "Classic"),
        new Book("Pride and Prejudice", "Jane Austen", "9781503290563", "Romance"),
        new Book("Moby Dick", "Herman Melville", "9781503280786", "Adventure"),
        new Book("The Catcher in the Rye", "J.D. Salinger", "9780316769488", "Fiction"),
        new Book("The Hobbit", "J.R.R. Tolkien", "9780547928227", "Fantasy"),
        new Book("Brave New World", "Aldous Huxley", "9780060850524", "Science Fiction"),
        new Book("Fahrenheit 451", "Ray Bradbury", "9781451673319", "Dystopian"),
        new Book("Crime and Punishment", "Fyodor Dostoevsky", "9780486415871", "Philosophical Fiction")
    ));

    public Catalog() {
    }

    static void displayResults(ArrayList<Book> results) {
        System.out.println("\n" + results.size() + " Results Found:");
        for(int i = 0; i<results.size(); i++) {
            System.out.print((i+1) + ". ");
            System.out.println(results.get(i));
        }
    }

    public void addBook(String title, String author, String isbn, String subject) {
        books.add(new Book(title, author, isbn, subject));
    }

    public boolean deleteBook(String isbn) {
        ArrayList<Book> result = searchBooks(isbn, "isbn");

        // Check if book found
        if (result.size() > 0) {
            books.remove(result.get(0));
            return true;
        }
        
        // Return false if book isn't found
        System.out.println("Book not found");
        return false;
    }

    public ArrayList<Book> searchBooks(String searchString, String type) {
        // New list for storing the result books
        ArrayList<Book> results = new ArrayList<>();
        searchString = searchString.toUpperCase();

        // Loop through books to find ones that have searchString
        for(Book book : books) {

            // Search for title
            if(type.equals("title")) {
                if(book.getTitle().toUpperCase().contains(searchString)) {
                    results.add(book);
                }
            }

            // Search for author
            else if(type.equals("author")) {
                if(book.getAuthor().toUpperCase().contains(searchString)) {
                    results.add(book);
                }
            }

            // Search for ISBN
            else if(type.equals("isbn")) {
                if(book.getIsbn().toUpperCase().contains(searchString)) {
                    results.add(book);
                }
            }
        }

        return results;
    }
}