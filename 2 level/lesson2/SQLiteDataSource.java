package lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Сергей on 29.01.2016.
 */
public class SQLiteDataSource {

    public static Connection getConnection() {
       /* try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }*/
        Connection conn = null;
        try {
            String dbURL = "jdbc:sqlite:product.db";
            conn = DriverManager.getConnection(dbURL);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return conn;
    }

    public static void main(String[] args) {
      /*  try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }*/
        JdbcExample ex = new JdbcExample();
        ex.insert();
        List<Foo> fooList = ex.selectEx();
        for (Iterator<Foo> iterator = fooList.iterator(); iterator.hasNext(); ) {
            Foo foo = iterator.next();
            System.out.println("foo = " + foo);
        }
    }
}
