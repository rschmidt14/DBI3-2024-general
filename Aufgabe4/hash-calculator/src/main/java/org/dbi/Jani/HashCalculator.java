package org.dbi.Jani;

import java.util.Scanner;
import java.sql.SQLException;

public class HashCalculator {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                if (choice == 1) {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    String passwordHash = Integer.toHexString(password.hashCode());

                    try {
                        userService.registerUser(new User(username, passwordHash));
                        System.out.println("User registered successfully!");
                    } catch (SQLException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                } else if (choice == 2) {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    String passwordHash = Integer.toHexString(password.hashCode());
                    if (userService.validateLogin(username, passwordHash)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                } else if (choice == 3) {
                    break;
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
