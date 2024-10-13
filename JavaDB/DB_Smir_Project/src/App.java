import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    Scanner myObj = new Scanner(System.in);
    String userInput = myObj.nextLine();
    if(userInput.equalsIgnoreCase("i")){
        System.out.println("Whats your name?");
        String userName = myObj.nextLine();
        System.out.println("Hello"+" "+userName+"!!");
    }

    myObj.close();
}
}
