package com.backend.pokeapp.shared.utils.password;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean check(String hash, String password) {
        return BCrypt.checkpw(password, hash);
    }
}
