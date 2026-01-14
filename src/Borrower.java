import java.util.ArrayList;
import java.io.Serializable;

public class Borrower extends Person implements BorrowingAccount, Serializable {
    private ArrayList<Loan> checkedOut = new ArrayList<>();
    private ArrayList<HoldRequest> holds = new ArrayList<>();

    public Borrower(String name, String address, String username, String password) {
        super(name, address, username, password);
    }

    public void printBooks() {
        // Print the loans
        System.out.println(checkedOut.size() + " Loans");
        for(int i = 0; i<checkedOut.size(); i++) {
            System.out.println((i+1) + ". " + checkedOut.get(i));
        }

        // Print the holds
        System.out.println("\n" + holds.size() + " Hold Requests");
        for(HoldRequest h: holds) {
            System.out.println(h);
        }

    }

    public void checkInBook(Loan loan) {
        loan.checkInBook();
        checkedOut.remove(loan);
    }

    public void checkOutBook(Book book) {
        book.checkOut();
        checkedOut.add(new Loan(book));
    }

    public void addHoldRequest(Book book) {
        book.placeOnHold();
        holds.add(new HoldRequest(book));
    }

    public ArrayList<HoldRequest> getReadyBooks() {
        // Get all the holds that are ready
        ArrayList<HoldRequest> ready = new ArrayList<>();
        for(HoldRequest h : holds) {
            if(h.getReady()) {
                ready.add(h);
            }
        }
        return ready;
    }

    public boolean hasBook(Book book) {
        // Check if in loans
        for(Loan l : checkedOut) {
            if(l.getBook().equals(book)) {
                return true;
            }
        }

        // Check if in holds
        for(HoldRequest h : holds) {
            if(h.getBook().equals(book)) {
                return true;
            }
        }

        return false;
    }

    public void checkOutHoldBook(HoldRequest hold) {
        // Checkout the book and remove from the hold list
        checkOutBook(hold.getBook());
        holds.remove(hold);
    }

    public ArrayList<Loan> getCheckedOut() {
        return checkedOut;
    }

    public ArrayList<HoldRequest> getHolds() {
        return holds;
    }

    public String toString() {
        return super.toString() + "   Total Loans: " + checkedOut.size() + "   Total Holds: " + holds.size();
    }
}