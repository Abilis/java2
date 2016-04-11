package ru.java2.lesson_2_2;

import java.sql.*;

/**
 * Created by Abilis on 11.04.2016.
 */
public class Db {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Conn.conn();
        Conn.createDb();
     //   Conn.writeDb();
        Conn.readDb();
        Conn.closeDb();
    }



}
