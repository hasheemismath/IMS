package Class;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider implements IProvider {

    private static ConnectionProvider CPIsntance;
    private static Connection connection;

    private ConnectionProvider() {

    }

    public static ConnectionProvider getInstance() {
        if (CPIsntance == null) {
            CPIsntance = new ConnectionProvider();
        }
        return CPIsntance;
    }

    public Connection getDBConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return connection;
    }

}
