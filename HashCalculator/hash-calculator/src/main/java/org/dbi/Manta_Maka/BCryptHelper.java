package org.dbi.Manta_Maka;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptHelper {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

