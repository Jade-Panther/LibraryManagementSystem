import java.util.Scanner;

public class Application {
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void main(String[] args) {
        Library library = new Library();

        library.run();
    }
}