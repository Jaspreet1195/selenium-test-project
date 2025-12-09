package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class DatabaseManager implements AutoCloseable {

  private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());

  private final Connection connection;

  public DatabaseManager() throws SQLException {
    this(
        PropertyReader.getOrDefault("db.url", "jdbc:h2:mem:testdb"),
        PropertyReader.getOrDefault("db.user", "sa"),
        PropertyReader.getOrDefault("db.password", ""),
        PropertyReader.getOrDefault("db.driver", "org.h2.Driver"));
  }

  public DatabaseManager(String url, String username, String password, String driver)
      throws SQLException {
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("Database driver not found: " + driver, e);
    }
    this.connection = DriverManager.getConnection(url, username, password);
  }

  public void createTable(String createQuery) throws SQLException {
    try (Statement stmt = connection.createStatement()) {
      stmt.execute(createQuery);
      LOGGER.info(() -> "Table created with statement: " + createQuery);
    }
  }

  public int insert(String query, Object... params) throws SQLException {
    return executeUpdate(query, params);
  }

  public List<Map<String, Object>> read(String query, Object... params) throws SQLException {
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      setParameters(stmt, params);
      try (ResultSet rs = stmt.executeQuery()) {
        return mapResultSet(rs);
      }
    }
  }

  public int update(String query, Object... params) throws SQLException {
    return executeUpdate(query, params);
  }

  public int delete(String query, Object... params) throws SQLException {
    return executeUpdate(query, params);
  }

  private int executeUpdate(String query, Object... params) throws SQLException {
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
      setParameters(stmt, params);
      return stmt.executeUpdate();
    }
  }

  private List<Map<String, Object>> mapResultSet(ResultSet rs) throws SQLException {
    List<Map<String, Object>> rows = new ArrayList<>();
    ResultSetMetaData meta = rs.getMetaData();
    int columnCount = meta.getColumnCount();

    while (rs.next()) {
      Map<String, Object> row = new HashMap<>();
      for (int i = 1; i <= columnCount; i++) {
        row.put(meta.getColumnLabel(i), rs.getObject(i));
      }
      rows.add(row);
    }
    return rows;
  }

  private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
    for (int i = 0; i < params.length; i++) {
      stmt.setObject(i + 1, params[i]);
    }
  }

  @Override
  public void close() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
      LOGGER.info("Database connection closed.");
    }
  }
}
