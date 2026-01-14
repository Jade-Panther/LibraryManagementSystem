import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.io.Serializable;

public class Loan implements Serializable {
    private static final double DAILY_FEE = 0.05;
    private LocalDate issuedDate, returnDate;
    private Book book;

    public Loan(Book book) {
        issuedDate = LocalDate.now();
        returnDate = issuedDate.plusDays(7);
        this.book = book;
    }

    public void checkInBook() {
        book.checkIn();
        if(isOverdue()) {
            System.out.println("Book overdue: you have received a $" + calculateFee() + " fine.");
        }
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(returnDate);
    }

    public String toString() {
        return "Loan for " + book + ", Due: " + returnDate;
    }

    public Book getBook() {
        return book;
    }

    public double calculateFee() {
        long daysLate = ChronoUnit.DAYS.between(returnDate, LocalDate.now());
        
        return daysLate * DAILY_FEE;
    }
}