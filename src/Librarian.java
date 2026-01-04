import java.io.Serializable;

public class Librarian extends Person implements Serializable {
    private String yearlySalary;

    public Librarian(String id, String name, String address, String email, String password, String salary) {
        super(id, name, address, email, password);
        yearlySalary = salary;
    }
}