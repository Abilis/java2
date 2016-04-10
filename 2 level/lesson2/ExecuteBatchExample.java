package lesson2;

import java.sql.*;

/**
 * Created by Сергей on 29.01.2016.
 */
public class ExecuteBatchExample {

    Statement stmt = null;
    ResultSet rs = null;
    Connection conn = ConnectionImpl.getInstance().getConnection();

    public int executeBatchEx() {
        String sql = "update foo set name=? where id=?";
        int total = 0;
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setObject(2, 1);
            preparedStatement.setObject(1, "foo111");
            // adding batches
            preparedStatement.addBatch();
            preparedStatement.setObject(2, 2);
            preparedStatement.setObject(1, "foo222");
            // adding batches
            preparedStatement.addBatch();
            // executing all batchs
            int[] updatedRecords = preparedStatement.executeBatch();
            for (int recordUpdated : updatedRecords) {
                total += recordUpdated;
            }
            System.out.println("total records updated by batch " + total);
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return total;
    }
}