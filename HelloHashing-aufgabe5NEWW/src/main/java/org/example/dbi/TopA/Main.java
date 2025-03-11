package org.example.dbi.TopA;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean register = false;
        if (args != null && args.length == 1 && args[0].equalsIgnoreCase("Register")) {
            register = true;
        }

        DataBaseService databaseService = new DataBaseService();
        databaseService.initializeDatabase();
        RegisterService registerService = new RegisterService(databaseService);
        LoginService loginService = new LoginService(databaseService);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (register) {
            if (registerService.registerUser(userName, password)) {
                System.out.println("Registrierung erfolgreich!");
            } else {
                System.out.println("Registrierung fehlgeschlagen.");
            }
        } else {
            if (loginService.loginUser(userName, password)) {
                System.out.println("Login erfolgreich!");
            } else {
                System.out.println("Falscher Benutzername oder Passwort!");
            }
        }
    }
}
