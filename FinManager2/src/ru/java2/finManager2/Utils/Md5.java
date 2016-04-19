package ru.java2.finManager2.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Abilis on 19.04.2016.
 */
public class Md5 {

    //метод преобразует входную строку в ее хэш md5 и возвращает результат
    public static String getMd5(String str) throws NoSuchAlgorithmException {

        String md5Hex = str;
        BigInteger bigInteger;

        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];


        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(str.getBytes());
        digest = messageDigest.digest();
        bigInteger = new BigInteger(1, digest);
        md5Hex = bigInteger.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }

}
