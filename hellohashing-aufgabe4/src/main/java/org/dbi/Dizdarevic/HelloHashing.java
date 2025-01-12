package org.dbi.Dizdarevic;

import java.util.Scanner;

import org.dbi.Dizdarevic.database.DatabaseService;
import org.dbi.Dizdarevic.database.LoginService;
import org.dbi.Dizdarevic.model.User;
import org.dbi.Dizdarevic.security.GuavaHashGenerator;
import org.dbi.Dizdarevic.security.HashGenerator;

public class HelloHashing {

    public static void main(String[] args) {
        HashGenerator hashGenerator = new GuavaHashGenerator();

        DatabaseService databaseService = new DatabaseService();
        LoginService loginService = new LoginService(databaseService);
        databaseService.initializeDatabase();

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Welcome! Please choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Enter your choice (1 or 2): ");
            String choice = scanner.nextLine();

            if (!choice.equals("1") && !choice.equals("2")) {
                System.out.println("Invalid choice. Exiting...");
                System.exit(0);
            }

            boolean register = choice.equals("2");

            System.out.print("Enter username: ");
            String userName = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = new User(userName, password);
            
            if (!loginService.validateUser(user)) {
                System.out.println("Invalid input. Exiting...");
                System.exit(-1);
            }

            user.setPasswordHash(hashGenerator.generateSha256Hex(user.getPassword()));

            if (register) {
                System.out.println("Registering user...");
                loginService.registerUser(user);
            } else {
                System.out.println("Logging in...");
                boolean loginSuccess = loginService.loginUser(user);
                if (!loginSuccess) {
                    System.out.println("Login failed.");
                }
            }
        } finally {
           //scanner.close(); // Close the scanner to avoid resource leaks
        }
    }
}
