import java.util.ArrayList;
import java.io.Serializable;

public interface BorrowingAccount {
    void printBooks();
    void checkInBook(Loan loan);
    void checkOutBook(Book book);
    void addHoldRequest(Book book);
    boolean hasBook(Book book);
    void checkOutHoldBook(HoldRequest hold);
    ArrayList<Loan> getCheckedOut();
    ArrayList<HoldRequest> getHolds();
    ArrayList<HoldRequest> getReadyBooks();
}