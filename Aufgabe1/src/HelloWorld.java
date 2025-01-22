import java.util.Scanner;

class HelloWorld {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Wie ist dein Name?");

        String userName = myObj.nextLine();
        System.out.println("Hallo " + userName + "!");
    }
}