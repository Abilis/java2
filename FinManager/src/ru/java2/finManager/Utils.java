package ru.java2.finManager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Abilis on 12.04.2016.
 */
public class Utils {


    //метод преобразует входную строку в ее хэш md5 и возвращает результат
    public static String getMd5(String str) {

        String md5Hex = str;
        BigInteger bigInteger;

        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes());
            digest = messageDigest.digest();
            bigInteger = new BigInteger(1, digest);
            md5Hex = bigInteger.toString(16);

            while (md5Hex.length() < 32) {
                md5Hex = "0" + md5Hex;
            }

        } catch (NoSuchAlgorithmException e) {
            ConsoleHelper.writeMessage("Не удалось сгенерировать хэш. Строка возвращается без изменений");
        }





        return md5Hex;
    }

}
