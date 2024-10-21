import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        
        if (args.length > 0 && args[0].equals("i")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Wie heißt du?");
            String name = scanner.nextLine();
            System.out.println("Servus " + name + "!");
            scanner.close();
        } else {
            System.out.println("Hello, World!");
        }
    }
}
