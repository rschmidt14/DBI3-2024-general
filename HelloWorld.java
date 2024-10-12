import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("i")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What's your name?");
            String name = scanner.nextLine();
            System.out.println("Hello " + name + "!");
        } else {
            System.out.println("Hello, World");
        }
    }
}

