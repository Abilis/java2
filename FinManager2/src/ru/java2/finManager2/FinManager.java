package ru.java2.finManager2;

import ru.java2.finManager2.gui.LoginAndRegistration;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;



/**
 * Created by Abilis on 19.04.2016.
 */
public class FinManager {


    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {

        //инициализации формы логина и регистрации
        LoginAndRegistration loginAndRegistration = new LoginAndRegistration();
        loginAndRegistration.init();






    }
}
