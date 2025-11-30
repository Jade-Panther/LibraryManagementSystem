public class Person {
    private String id, name, address, email, password;

    public Person(String id, String name, String address, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
    }

    public void printInfo() {
        System.out.println(id + " Name: " + name + " Address: " + address + "Email: " + email + " Password: " + password);
    }
}