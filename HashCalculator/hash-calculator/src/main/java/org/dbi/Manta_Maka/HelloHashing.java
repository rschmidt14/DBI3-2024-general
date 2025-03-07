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
        hashGenerator = new GuavaHashGenerator();
        //hashGenerator = new CommonsHashGenerator();
        boolean login = false;
        if (args != null && args.length == 1 && args[0].equalsIgnoreCase("Login")) {
            login = true;
        }
        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Benutzername und Passwort einlesen
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String passwordHash = hashGenerator.generateSha256Hex(password);

        // Hashing
        //System.out.println("Hash is: " + passwordHash);

        // Verbindung zur Datenbank herstellen
        try (Connection connection = ConnectionFactory.getConnection()) {
            if (connection != null) {
                System.out.println("Verbindung zur Datenbank erfolgreich hergestellt!");
                if (login == false) {
                    // Benutzerregistrierung
                    registerUser(userName, passwordHash, connection);
                } else {
                    // Benutzer-Login
                    loginUser(userName, passwordHash, connection);
                }
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
    private static void registerUser(String username, String passwordHash, Connection connection) throws SQLException {
        UserService userService = new UserService(connection);

        // Überprüfen, ob der Benutzer bereits existiert
        if (userService.findUserByUsername(username) != null) {
            System.out.println("Benutzername bereits vergeben. Bitte wähle einen anderen.");
            return; // Beende den Registrierungsvorgang
        }

        // Benutzer speichern
        User user = new User(username, passwordHash);
        userService.saveUser(user);
        System.out.println("Password Hash on Register: " + passwordHash);

        System.out.println("Registrierung erfolgreich!");
    }

    // Methode für den Benutzer-Login
    private static void loginUser(String username, String passwordHash, Connection connection) throws SQLException {
        UserLoginService userLoginService = new UserLoginService();

        // Login mit UserLoginService
        boolean loginSuccess = userLoginService.login(username, passwordHash);
        if (loginSuccess) {
            System.out.println("Login erfolgreich! Willkommen, " + username + "!");
        } else {
            System.out.println("Login fehlgeschlagen! Überprüfen Sie Ihre Anmeldedaten.");
        }
    }
}
