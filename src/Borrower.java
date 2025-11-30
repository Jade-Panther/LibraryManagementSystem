import java.util.ArrayList;

public class Borrower extends Person {
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
}