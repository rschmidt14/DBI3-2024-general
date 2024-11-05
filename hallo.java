import java.util.Scanner;

public class hallo {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("i")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Name?");
            String name = scanner.nextLine();
            System.out.println("Hallo " + name + "!");
            scanner.close();
        } else {
            System.out.println("Hello World!");
        }
    }
}