package lesson2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 28.01.2016.
 */
public class JDBC4Example {

    Statement stmt = null;
    ResultSet rs = null;
    Connection conn = ConnectionImpl.getInstance().getConnection();

    public List<Foo> selectEx() {
        List fooList = new ArrayList();
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery("SELECT * FROM foo");
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...

              /*  if (stmt.execute("SELECT foo FROM bar")) {
                    rs = stmt.getResultSet();
                }*/ // Now do something with the ResultSet ....
            int i = 0;
            while (rs.next()) {

                int id = rs.getInt(1);
                String name = rs.getString(2);
                fooList.add(i, new Foo(id, name));
                i++;

            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return fooList;

    }
}
