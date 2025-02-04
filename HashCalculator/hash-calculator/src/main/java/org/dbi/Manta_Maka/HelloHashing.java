package org.dbi.Manta_Maka;

import java.util.Scanner;
import org.dbi.Manta_Maka.GuavaHashGenerator;
import org.dbi.Manta_Maka.HashGenerator;
import java.sql.Connection;
import java.sql.SQLException;

public class HelloHashing {
    public static void main(String[] args) {

        // HashGenerator
        HashGenerator hashGenerator = null;
        if (args != null && args.length == 1 && args[0].equalsIgnoreCase("Guava")) {
            hashGenerator = new GuavaHashGenerator();
        } else if (args != null && args.length == 1 && args[0].equalsIgnoreCase("Commons")) {
            hashGenerator = new CommonsHashGenerator();
        } else {
            System.out.println("Usage: HelloHashing [Guava | Commons]");
            return;
        }

        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Benutzername und Passwort einlesen
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Hashing
        System.out.println("Playground Hash is: " + hashGenerator.generatePlaygroundHash(password));
        System.out.println("Hash is: " + hashGenerator.generateSha256Hex(password));

        // Verbindung zur Datenbank herstellen
        try (Connection connection = ConnectionFactory.getConnection()) {
            if (connection != null) {
                System.out.println("Verbindung zur Datenbank erfolgreich hergestellt!");

                // Benutzerregistrierung
                registerUser(scanner, connection);

                // Benutzer-Login
                loginUser(scanner, connection);
            }
        } catch (SQLException e) {
            System.err.println("Fehler bei der Datenbankverbindung: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Scanner schließen
            scanner.close();
        }
    }

    // Methode zur Benutzerregistrierung
    private static void registerUser(Scanner scanner, Connection connection) throws SQLException {
        UserService userService = new UserService(connection);

        System.out.println("=== Benutzer Registrierung ===");

        System.out.print("Benutzernamen eingeben: ");
        String username = scanner.nextLine().trim();

        // Überprüfen, ob der Benutzer bereits existiert
        if (userService.findUserByUsername(username) != null) {
            System.out.println("Benutzername bereits vergeben. Bitte wähle einen anderen.");
            return; // Beende den Registrierungsvorgang
        }

        System.out.print("Passwort eingeben: ");
        String userPassword = scanner.nextLine();

        // Passwort hashen
        String passwordHash = BCryptHelper.hashPassword(userPassword);

        // Benutzer speichern
        User user = new User(username, passwordHash);
        userService.saveUser(user);

        System.out.println("Registrierung erfolgreich!");
    }

    // Methode für den Benutzer-Login
    private static void loginUser(Scanner scanner, Connection connection) throws SQLException {
        UserLoginService userLoginService = new UserLoginService();

        System.out.println("=== Benutzer Login ===");

        System.out.print("Benutzernamen eingeben: ");
        String username = scanner.nextLine().trim();

        System.out.print("Passwort eingeben: ");
        String userPassword = scanner.nextLine();

        // Login mit UserLoginService
        boolean loginSuccess = userLoginService.login(username, userPassword);
        if (loginSuccess) {
            System.out.println("Login erfolgreich! Willkommen, " + username + "!");
        } else {
            System.out.println("Login fehlgeschlagen! Überprüfen Sie Ihre Anmeldedaten.");
        }
    }
}
