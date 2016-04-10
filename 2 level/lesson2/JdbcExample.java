package lesson2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Сергей on 27.01.2016.
 */
public class JdbcExample {

// assume that conn is an already created JDBC connection (see previous examples)

    Statement stmt = null;
    ResultSet rs = null;
    Connection conn = ConnectionImpl.getInstance().getConnection();

    public List<Foo> selectEx() {
        List fooList = new ArrayList();
        try {
            stmt = conn.createStatement();
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
        } finally {
            closeResources();
        }
        return fooList;
    }

    private void closeResources() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException sqlEx) {
            } // ignore
            rs = null;
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException sqlEx) {
            } // ignore
            stmt = null;
        }
    }

    public void insert() {
        try {
            stmt = conn.createStatement();
            //stmt.executeUpdate("DROP TABLE IF EXISTS foo");

         /*   stmt.executeUpdate(
                    "CREATE TABLE foo ("
                            + "id INT NOT NULL AUTO_INCREMENT, "
                            + "name VARCHAR(64), PRIMARY KEY (id))");*/
            int res = stmt.executeUpdate(
                    "INSERT INTO foo (name) "
                            + "values ('Foo" + Statement.RETURN_GENERATED_KEYS + "')",
                    Statement.RETURN_GENERATED_KEYS);
            int res2 = stmt.executeUpdate(
                    "INSERT INTO foo (name) "
                            + "values ('Foo" + Statement.RETURN_GENERATED_KEYS + "')",
                    Statement.RETURN_GENERATED_KEYS);
            int res3 = stmt.executeUpdate(
                    "INSERT INTO foo (name) "
                            + "values ('Foo" + Statement.RETURN_GENERATED_KEYS + "')",
                    Statement.RETURN_GENERATED_KEYS);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
            closeResources();
        }
    }
}
