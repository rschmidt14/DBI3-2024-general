package org.dbi.Manta_Maka;

import java.util.Scanner;
import org.dbi.Manta_Maka.GuavaHashGenerator;
import org.dbi.Manta_Maka.HashGenerator;

public class HelloHashing {
    public static void main(String[] args) {

        HashGenerator hashGenerator = null;
        hashGenerator = new GuavaHashGenerator();
        if(args != null && args.length == 1 && args[0].equalsIgnoreCase("Guava")) {
            hashGenerator = new GuavaHashGenerator();
        } else if(args != null && args.length == 1 && args[0].equalsIgnoreCase("Commons")) {
            hashGenerator = new CommonsHashGenerator();
        } else {
            System.out.println("Usage: HelloHashing [Guava | Commons]");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Playground Hash is: "+hashGenerator.generatePlaygroundHash(password));

        //System.out.println("Hash ("+args[0]+") is: " + hashGenerator.generateSha256Hex(password));
        System.out.println("Hash is: " + hashGenerator.generateSha256Hex(password));

        scanner.close();
    }
}
