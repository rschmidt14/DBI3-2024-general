package org.dbi.hess;

import org.dbi.hess.security.HashGenerator;
import org.dbi.hess.security.CommonsHashGenerator;
import java.sql.SQLException;
import java.util.Scanner;

public class UserManagement {
    private static UserService userService = new UserService();
    private static Scanner scanner = new Scanner(System.in);
    private static HashGenerator hashGenerator = new CommonsHashGenerator();

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Registrieren");
            System.out.println("2. Anmelden");
            System.out.println("3. Beenden");
            System.out.print("Wählen Sie eine Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Auf Wiedersehen!");
                    return;
                default:
                    System.out.println("Ungültige Option.");
            }
        }
    }

    private static void register() {
        System.out.print("Benutzername: ");
        String username = scanner.nextLine();
        System.out.print("Passwort: ");
        String password = scanner.nextLine();

        try {
            if (userService.getUserByUsername(username) != null) {
                System.out.println("Benutzername bereits vergeben.");
                return;
            }

            String passwordHash = hashGenerator.generateSha256Hex(password);
            User newUser = new User(username, password, passwordHash);
            userService.saveUser(newUser);
            System.out.println("Registrierung erfolgreich.");
        } catch (SQLException e) {
            System.out.println("Fehler bei der Registrierung: " + e.getMessage());
        }
    }

    private static void login() {
        System.out.print("Benutzername: ");
        String username = scanner.nextLine();
        System.out.print("Passwort: ");
        String password = scanner.nextLine();

        try {
            User user = userService.getUserByUsername(username);
            if (user != null && verifyPassword(password, user.getPasswordHash())) {
                System.out.println("Anmeldung erfolgreich.");
            } else {
                System.out.println("Ungültige Anmeldedaten.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Anmeldung: " + e.getMessage());
        }
    }

    private static boolean verifyPassword(String inputPassword, String storedHash) {
        String inputHash = hashGenerator.generateSha256Hex(inputPassword);
        return inputHash.equals(storedHash);
    }
}

