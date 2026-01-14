import java.util.ArrayList;
import java.util.Arrays;
import java.io.Serializable;

public class Catalog implements Serializable {
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
        new Book("Crime and Punishment", "Fyodor Dostoevsky", "9780486415871", "Philosophical Fiction"),
        new Book("The Lord of the Rings", "J.R.R. Tolkien", "9780618640157", "Fantasy"),
        new Book("Animal Farm", "George Orwell", "9780451526342", "Political Satire"),
        new Book("Jane Eyre", "Charlotte Brontë", "9780141441146", "Classic"),
        new Book("Wuthering Heights", "Emily Brontë", "9780141439556", "Gothic Fiction"),
        new Book("The Chronicles of Narnia", "C.S. Lewis", "9780066238500", "Fantasy"),
        new Book("The Grapes of Wrath", "John Steinbeck", "9780143039433", "Historical Fiction"),
        new Book("The Alchemist", "Paulo Coelho", "9780062315007", "Philosophical Fiction"),
        new Book("The Book Thief", "Markus Zusak", "9780375842207", "Historical Fiction"),
        new Book("The Kite Runner", "Khaled Hosseini", "9781594631931", "Drama"),
        new Book("Life of Pi", "Yann Martel", "9780156027328", "Adventure"),
        new Book("The Hunger Games", "Suzanne Collins", "9780439023481", "Dystopian"),
        new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", "9780590353427", "Fantasy"),
        new Book("The Da Vinci Code", "Dan Brown", "9780307474278", "Mystery"),
        new Book("The Shining", "Stephen King", "9780307743657", "Horror"),
        new Book("Dracula", "Bram Stoker", "9780486411095", "Gothic Horror")
    ));

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

    public ArrayList<Book> getAllBooks() {
        return books;
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