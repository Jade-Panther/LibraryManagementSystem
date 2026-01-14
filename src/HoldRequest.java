
import java.time.LocalDate;
import java.io.Serializable;

public class HoldRequest implements Serializable {
    private int placeInLine;
    private Book book;
    private LocalDate issuedDate;
    private boolean ready;

    public HoldRequest(Book book) {
        issuedDate = LocalDate.now();
        this.book = book;
        ready = false;
    }

    public Book getBook() {
        return book;
    }

    public void setReady() {
        ready = true;
    }
    public boolean getReady() {
        return ready;
    }

    public String toString() {
        return "Request for " + book + ", issued: " + issuedDate;
    }
}