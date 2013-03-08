package com.kkarlberg.conan.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.ByteSource;

public class PassHash {

    public static Sha256Hash hashNSaltPass(String plainPass, int hashIterations){
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        ByteSource salt = rng.nextBytes();
        return new Sha256Hash(plainPass, salt, hashIterations);
    }

    public static void main(String[] args) {
        String plain = "pass2";
        Sha256Hash sha = hashNSaltPass(plain , 1024);
        System.err.println("Plain: "+plain+", Hashed[hex]: "+sha.toHex()+" Hashed[base64]: "+sha.toBase64()+ " salt: "+sha.getSalt()+" salt as base64: "+sha.getSalt().toHex());
    }
}

