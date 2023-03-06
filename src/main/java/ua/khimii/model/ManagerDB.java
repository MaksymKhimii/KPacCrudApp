package ua.khimii.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ManagerDB {
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static ManagerDB instance;
    private static Connection connection;

    static {
        URL = "jdbc:mysql://localhost:3306/knowledge_package?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        USERNAME = "Maks_Khimii";
        PASSWORD = "root";
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
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        return connection;
    }

    public static void setAutocommit() {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
