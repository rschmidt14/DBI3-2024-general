package Aufgabe4.src.main;

import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {
    private static UserService userService = new UserService();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" _   _              _      _____         _               _         _                ");
        System.out.println("| | | |            | |    /  __ \\       | |             | |       | |               ");
        System.out.println("| |_| |  __ _  ___ | |__  | /  \\/  __ _ | |  ___  _   _ | |  __ _ | |_   ___   _ __ ");
        System.out.println("|  _  | / _` |/ __|| '_ \\ | |     / _` || | / __|| | | || | / _` || __| / _ \\ | '__|");
        System.out.println("| | | || (_| |\\__ \\| | | || \\__/\\| (_| || || (__ | |_| || || (_| || |_ | (_) || |   ");
        System.out.println("\\_| |_/ \\__,_||___/|_| |_| \\____/ \\__,_||_| \\___| \\__,_||_| \\__,_| \\__| \\___/ |_|  ");


        while (true) {
            System.out.println("[1] Login");
            System.out.println("[2] Registrieren");
            System.out.println("[3] Beenden");
            System.out.print("Wahl: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    login(scanner);
                    break;
                case "2":
                    register(scanner);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Falsche Eingabe");
            }
        }
    }


    private static void login(Scanner scanner) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Passwort: ");
        String password = scanner.nextLine();
        String passwordHash = hashPassword(password);

        User user = userService.loginUser(username, passwordHash);
        if (user != null) {
            System.out.println("Erfolgreich");
        } else {
            System.out.println("Falsche Eingabe");
        }
    }

    private static void register(Scanner scanner) {
        System.out.println("Nutzer registrieren");
        System.out.println("-------------------");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Passwort: ");
        String password = scanner.nextLine();
        String passwordHash = hashPassword(password);

        if (userService.registerUser(username, passwordHash)) {
            System.out.println("Nutzer erstellt");
        } else {
            System.out.println("fail");
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
