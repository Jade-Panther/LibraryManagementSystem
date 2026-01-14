import java.io.Serializable;

public class Book implements Serializable {
    private final String title, author, isbn, subject;
    private Status status;

    public Book(String title, String author, String isbn, String subject) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.subject = subject;
        status = Status.AVAILABLE;
    }
    
    public String toString() {
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Subject: " + subject + ", Status: " + status;
    }

    public void checkIn() {
        status = Status.AVAILABLE;
    }

    public void checkOut() {
        status = Status.BORROWED;
    }

    public void placeOnHold() {
        status = Status.HELD;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Status getStatus() {
        return status;
    }
}