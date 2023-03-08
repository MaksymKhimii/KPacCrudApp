package ua.khimii.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ManagerDB {
    private static final ResourceBundle properties;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static ManagerDB instance;

    static {
        properties = ResourceBundle.getBundle("application");
        URL = properties.getString("my-sql.url");
        USERNAME = properties.getString("my-sql.user");
        PASSWORD = properties.getString("my-sql.password");
    }

    private ManagerDB() {
    }

    synchronized public static ManagerDB getInstance() {
        if (instance == null) {
            instance = new ManagerDB();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return connection;
    }
}
