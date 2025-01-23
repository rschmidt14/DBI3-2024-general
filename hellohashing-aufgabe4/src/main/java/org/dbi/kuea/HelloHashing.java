package org.dbi.kuea;

import org.dbi.kuea.database.DatabaseService;
import org.dbi.kuea.database.LoginService;
import org.dbi.kuea.model.User;
import org.dbi.kuea.security.GuavaHashGenerator;
import org.dbi.kuea.security.HashGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class HelloHashing {

    private static final Logger LOG = LoggerFactory.getLogger(HelloHashing.class);

    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        LoginService loginService = new LoginService(databaseService);
        databaseService.initializeDatabase();
        Scanner scanner = new Scanner(System.in);

        HashGenerator hashGenerator = new GuavaHashGenerator();
        boolean register = false;

        System.out.println(" _    _      _ _       _    _           _     _             ");
        System.out.println("| |  | |    | | |     | |  | |         | |   (_)            ");
        System.out.println("| |__| | ___| | | ___ | |__| | __ _ ___| |__  _ _ __   __ _ ");
        System.out.println("|  __  |/ _ \\ | |/ _ \\|  __  |/ _` / __| '_ \\| | '_ \\ / _` |");
        System.out.println("| |  | |  __/ | | (_) | |  | | (_| \\__ \\ | | | | | | | (_| |");
        System.out.println("|_|  |_|\\___|_|_|\\___/|_|  |_|\\__,_|___/_| |_|_|_| |_|\\__, |");
        System.out.println("                                                       __/ |");
        System.out.println("                                                      |___/");


        while (true) {
            System.out.println("[1] Login");
            System.out.println("[2] Registrieren");
            System.out.print("Wahl: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    register = false;
                    break;
                case "2":
                    register = true;
                    break;
                default:
                    System.out.println("Falsche Eingabe");
            }


            System.out.print("Enter username: ");
            String userName = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            User user = new User(userName, password);
            if (!loginService.validateUser(user)) {
                LOG.info("Input invalid!");
                System.exit(-1);
            }

            user.setPasswordHash(hashGenerator.generateSha256Hex(user.getPassword()));

            if (register) {
                loginService.registerUser(user);
            } else {
                loginService.loginUser(user);
            }
        }
    }
}
