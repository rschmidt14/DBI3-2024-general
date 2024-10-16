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
            Scanner myObj = new Scanner(System.in);
            System.out.println("What's your name?");

            String userName = myObj.nextLine();
            System.out.println("Hello " + userName + "!"); // Output user input

            myObj.close();
        } else {
            System.out.println("Hello World!");
        }
    };
}
