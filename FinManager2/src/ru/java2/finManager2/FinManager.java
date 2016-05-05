package ru.java2.finManager2;

import ru.java2.finManager2.gui.LoginAndRegistration;
import ru.java2.finManager2.utils.InitDb;
import ru.java2.finManager2.utils.InitSqliteDB;


/**
 * Created by Abilis on 19.04.2016.
 */
public class FinManager {


    public static void main(String[] args) {

        //инициализация БД
//        InitDb.initDb(); //инициализация БД MySQL
        InitSqliteDB.initSqlitedDb();   //инициализация БД SQLite


        //инициализации формы логина и регистрации
        LoginAndRegistration loginAndRegistration = new LoginAndRegistration();
        loginAndRegistration.init();






    }
}
