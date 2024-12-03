package org.dbi.Jani;
import java.util.Scanner;

/**
 * Hello world!
 */
public class HashCalculator {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Bitte Passwort eingeben:");
        String password = scanner.nextLine();

        HashGenerator generator = null;

        if (args.length > 0 && args[0].equalsIgnoreCase("commons")) {
            generator = new CommonsHashGenerator();
        } else {
            generator = new GuavaHashGenerator();
        }

        System.out.println("Playground Hash: " + generator.generatePlaygroundHash(password));
        System.out.println("SHA256 Hash: " + generator.generateSha256Hex(password));
    }
}
