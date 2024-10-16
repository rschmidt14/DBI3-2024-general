import java.util.Scanner;

class HelloWorld {
    public static void main(String[] args) {
        boolean iFlag = false;

        for (String arg : args) {
            if (arg.equals("i")) {
                iFlag = true;
                break;
            }
        }

        if (iFlag) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What's your name?");

            String name = scanner.nextLine();
            System.out.println("Hello " + name + "!");

            scanner.close();
        } else {
            System.out.println("Hello World!");
        }
    };
}
