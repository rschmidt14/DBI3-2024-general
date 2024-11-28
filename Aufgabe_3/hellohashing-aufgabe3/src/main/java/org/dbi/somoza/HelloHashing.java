package org.dbi.somoza;

import java.util.Scanner;

import org.dbi.somoza.security.CommonsHashGenerator;
import org.dbi.somoza.security.GuavaHashGenerator;
import org.dbi.somoza.security.HashGenerator;

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
        System.out.print("Geben Sie ihre username: ");
        String userName = scanner.nextLine();

        System.out.print("Geben Sie ihre password: ");
        String password = scanner.nextLine();

        System.out.println("Anzahl der Buchstabe ist: "+hashGenerator.generatePlaygroundHash(password));

        System.out.println("Ihre Hash ist: " + hashGenerator.generateSha256Hex(password));

    }
}

