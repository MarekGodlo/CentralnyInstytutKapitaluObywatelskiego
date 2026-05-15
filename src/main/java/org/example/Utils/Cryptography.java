package org.example.Utils;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

public class Cryptography {
    Argon2PasswordEncoder argon;

    public Cryptography(Argon2PasswordEncoder argon) {
        this.argon = argon;
    }

    public String hash(String password) {
       return argon.encode(password);
    }

    public boolean verify(String password, String hash) {
        return argon.matches(password, hash);
    }
}
