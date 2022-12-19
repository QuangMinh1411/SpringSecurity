package com.heaven.usernamepassword.security;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Component
@NoArgsConstructor
public class KeyGeneratorUtils {
    static KeyPair generateKey(){
        KeyPair keyPair;
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair=keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex){
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }
}
