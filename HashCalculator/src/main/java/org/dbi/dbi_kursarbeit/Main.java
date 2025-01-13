package org.dbi.dbi_kursarbeit;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Willkommen im Benutzerverwaltungssystem!");
        System.out.println("Wählen Sie eine Option:");
        System.out.println("1. Registrieren");
        System.out.println("2. Anmelden");
        System.out.print("Ihre Wahl: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Konsolenpuffer leeren

        if (choice == 1) {
            // Registrierung
            System.out.print("Benutzernamen eingeben: ");
            String username = scanner.nextLine();
            System.out.print("Passwort eingeben: ");
            String password = scanner.nextLine();

            // Passwort hashen
            String passwordHash = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);

            // Benutzerobjekt erstellen
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setPasswordHash(passwordHash);

            // Benutzer zur Datenbank hinzufügen
            userService.addUser(user);
            System.out.println("Benutzer erfolgreich registriert!");
        } else if (choice == 2) {
            // Anmeldung
            System.out.print("Benutzernamen eingeben: ");
            String username = scanner.nextLine();
            System.out.print("Passwort eingeben: ");
            String password = scanner.nextLine();

            // Benutzer aus der Datenbank abrufen
            User user = userService.getUser(username);

            if (user != null && user.getPasswordHash().equals(org.apache.commons.codec.digest.DigestUtils.sha256Hex(password))) {
                System.out.println("Anmeldung erfolgreich! Willkommen, " + user.getUsername());
            } else {
                System.out.println("Ungültiger Benutzername oder Passwort.");
            }
        } else {
            System.out.println("Ungültige Wahl. Anwendung wird beendet.");
        }
    }
}