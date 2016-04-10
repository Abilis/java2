package lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Сергей on 27.01.2016.
 */
public class MySqlDataSource {
    // Notice, do not import com.mysql.jdbc.*
    // or you will have problems!
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
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                            "user=mysql&password=mysql123&useSSL=false");
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return conn;
    }

    public static void main(String[] args) {
        JdbcExample ex = new JdbcExample();
        ex.insert();

        /*ExecuteBatchExample ex2 = new ExecuteBatchExample();
        int res = ex2.executeBatchEx();
        System.out.println("res = " + res);
*/
        List<Foo> fooList = ex.selectEx();
        for (Iterator<Foo> iterator = fooList.iterator(); iterator.hasNext(); ) {
            Foo foo = iterator.next();
            System.out.println("foo = " + foo);
        }
    }
}
