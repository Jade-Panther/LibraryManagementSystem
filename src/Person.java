
import java.io.Serializable;

public class Person implements Serializable{
    private String id, name, address, email, password;

    public Person(String id, String name, String address, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public String toString() {
        return id + " Name: " + name + ", Address: " + address + ", Email: " + email + ", Password: " + password;
    }
}