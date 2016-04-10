package lesson2;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;


/**
 * Created by Сергей on 30.01.2016.
 */
public class CachedRowSetExample {

    Statement stmt = null;
    ResultSet rs = null;
    Connection conn = ConnectionImpl.getInstance().getConnection();

    public void selectCachedEx() {
        String sql = "SELECT * FROM foo";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            CachedRowSet crs = new CachedRowSetImpl();
            crs.populate(rs);
            while (crs.next()) {
                String name = crs.getString(2);
                int id = crs.getInt(1);
                System.out.println(name + "  " + id + "  ");

                crs.updateString(2, "cachedFoo");
                crs.updateRow();
                crs.moveToCurrentRow();

                crs.acceptChanges();
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }

    }
}
