import java.util.Scanner;

class HelloWorld {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("i")) {
            Scanner myObject = new Scanner(System.in);
            System.out.println("What's your name?");

            String name = myObject.nextLine();
            System.out.println("Hello " + name +"!");

        } else {
            System.out.println("False argument typed");
            }
    }
}
