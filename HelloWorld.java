import java.util.Scanner;

class HelloWorld {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("i")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What's your name?");
            String name = scanner.nextLine();
            System.out.println("Hello " + name + "!");
            scanner.close();
        } else {
            System.out.println("Hello World!");
        }
    }
}
