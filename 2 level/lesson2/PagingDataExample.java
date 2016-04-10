package lesson2;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

/**
 * Created by Сергей on 30.01.2016.
 */
public class PagingDataExample {

    Statement stmt = null;
    ResultSet rs = null;
    Connection conn = ConnectionImpl.getInstance().getConnection();


    public void selectCachedEx() {
        String sql = "SELECT * FROM foo";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            CachedRowSet crs = new CachedRowSetImpl();
            crs.populate(rs);

            crs.setPageSize(100);
            crs.execute(conn);

            while (crs.nextPage()) {
                while (crs.next()) {
                    // operate on chunks (of 100 rows each) in crs,
                    // row by row
                }
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("VendorError: " + sqle.getErrorCode());
        }
    }
}
