import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private LocalDate issuedDate, returnDate;
    private final double DAILY_FEE = 0.05;
    private final double LOST_FEE = 5;
    private Book book;

    public Loan(Book book) {
        issuedDate = LocalDate.now();
        returnDate = issuedDate.plusDays(7);
        this.book = book;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(returnDate);
    }

    public double calculateFee() {
        long daysLate = ChronoUnit.DAYS.between(returnDate, LocalDate.now());
        
        return daysLate * DAILY_FEE;
    }
}