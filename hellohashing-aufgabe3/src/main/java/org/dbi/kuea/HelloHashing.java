package org.dbi.kuea;

import org.dbi.kuea.security.CommonsHashGenerator;
import org.dbi.kuea.security.GuavaHashGenerator;
import org.dbi.kuea.security.HashGenerator;

import java.util.Scanner;

public class HelloHashing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Benutzername: ");
        String username = scanner.nextLine();

        System.out.print("Passwort: ");
        String password = scanner.nextLine();

        System.out.print("Wählen Sie den Hash-Generator (1 = Guava, 2 = Commons): ");
        int choice = scanner.nextInt();

        HashGenerator generator;

        if (choice == 1) {
            generator = new GuavaHashGenerator();
        } else if (choice == 2) {
            generator = new CommonsHashGenerator();
        } else {
            System.out.println("Ungültige Auswahl!");
            return;
        }

        System.out.println("Playground Hash (Länge): " + generator.generatePlaygroundHash(password));
        System.out.println("SHA256 Hash: " + generator.generateSha256Hex(password));
    }
}
