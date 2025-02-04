package org.dbi.Manta_Maka;

import java.sql.Connection;

public class UserLoginService {

    public static boolean login(String username, String password) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            UserService userService = new UserService(connection);

            // Benutzer suchen
            User user = userService.findUserByUsername(username);
            if (user == null) {
                return false; // Benutzer nicht gefunden
            }

            // Passwort überprüfen
            return BCryptHelper.checkPassword(password, user.getPasswordHash());
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Fehler beim Login
        }
    }
}
