package org.dbi.smir;

import java.awt.*;
import java.util.Scanner;

import org.dbi.smir.database.DatabaseService;
import org.dbi.smir.database.LoginService;
import org.dbi.smir.model.User;
import org.dbi.smir.security.CommonsHashGenerator;
import org.dbi.smir.security.GuavaHashGenerator;
import org.dbi.smir.security.HashGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloHashing {

    private static final Logger LOG = LoggerFactory.getLogger(HelloHashing.class);

    public static void main(String[] args) {

        HashGenerator hashGenerator = new GuavaHashGenerator();
        boolean register = false;
        if (args != null && args.length == 1 && args[0].equalsIgnoreCase("Register")) {
            //todo: change register flag here
        } else if (args != null && args.length > 0) {
            System.out.println("Usage: HelloHashing [Register]");
            return;
        }

        DatabaseService databaseService = new DatabaseService();
        LoginService loginService = new LoginService(databaseService);
        databaseService.initializeDatabase();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String userName = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = new User(userName, password);
        if (!loginService.validateUser(user)) {
            LOG.info("Input invalid!");
            System.exit(-1);
        }

        //todo: add the password hash to the user - Erledigt
        String passwort_normal = user.getPassword();
        String passwort_hash = hashGenerator.generateSha256Hex(passwort_normal);
        user.setPasswordHash(passwort_hash);


        if (register) {
            loginService.registerUser(user);
        } else {
            loginService.loginUser(user);
        }
    }
}
