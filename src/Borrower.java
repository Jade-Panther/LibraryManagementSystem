import java.util.ArrayList;
import java.io.Serializable;

public class Borrower extends Person implements Serializable{
    private ArrayList<Loan> checkedOut = new ArrayList<>();
    private ArrayList<HoldRequest> holds = new ArrayList<>();

    public Borrower(String id, String name, String address, String username, String password) {
        super(id, name, address, username, password);
    }

    public void checkOutBook(Book book) {
        checkedOut.add(new Loan(book));
    }

    public void addHoldRequest(Book book) {
        holds.add(new HoldRequest(book));
    }

    public String toString() {
        return super.toString() + "   Total Loans: " + checkedOut.size() + "   Total Holds: " + holds.size();
    }
}