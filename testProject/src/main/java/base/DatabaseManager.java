package base;

import java.sql.*;


public class DatabaseManager {

    private static final String JDBC_URL = "jdbc:h2:mem:testdb"; // in-memory database
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";
    private static final String JDBC_DRIVER = "org.h2.Driver";

    private Connection connection;

    // Constructor - establishes connection
    public DatabaseManager() {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println(" H2 Database connected successfully!");
            
         // ✅ Added validation check
            if (connection == null) {
                throw new SQLException("Database connection failed. Check if H2 driver is available.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------- CREATE TABLE -----------------
    public void createTable(String createQuery) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createQuery);
            System.out.println("✅ Table created successfully!");
        }
    }

    // ----------------- INSERT -----------------
    public void insert(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParameters(stmt, params);
            stmt.executeUpdate();
            System.out.println("✅ Insert successful!");
        }
    }

    // ----------------- READ -----------------
    public void read(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParameters(stmt, params);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(meta.getColumnName(i) + ": " + rs.getObject(i) + " | ");
                }
                System.out.println();
            }
        }
    }

    // ----------------- UPDATE -----------------
    public void update(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParameters(stmt, params);
            int rows = stmt.executeUpdate();
            System.out.println("✅ Update successful! Rows affected: " + rows);
        }
    }

    // ----------------- DELETE -----------------
    public void delete(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            setParameters(stmt, params);
            int rows = stmt.executeUpdate();
            System.out.println("✅ Delete successful! Rows affected: " + rows);
        }
    }

    // ----------------- Utility: Set PreparedStatement Params -----------------
    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    // ----------------- Close Connection -----------------
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("❎ H2 Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
