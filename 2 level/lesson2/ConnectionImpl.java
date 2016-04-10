package lesson2;

import java.sql.Connection;

/**
 * Created by Сергей on 27.01.2016.
 */
public class ConnectionImpl {

    private static ConnectionImpl ourInstance = new ConnectionImpl();

    public static ConnectionImpl getInstance() {
        return ourInstance;
    }

    public Connection getConnection() {
        Connection conn = MySqlDataSource.getConnection();
        if (conn != null) return conn;
        else {
            return SQLiteDataSource.getConnection();
        }
    }

    private ConnectionImpl() {
    }
}
