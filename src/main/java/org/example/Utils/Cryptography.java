package org.example.Utils;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Cryptography {
    Argon2PasswordEncoder argon = new Argon2PasswordEncoder(16,32,1,60000,10);

    public String hash(String password) {
       return argon.encode(password);
    }

    public boolean verify(String password, String hash) {
        return argon.matches(password, hash);
    }
}
