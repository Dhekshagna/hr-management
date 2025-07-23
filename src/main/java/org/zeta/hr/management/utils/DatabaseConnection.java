package org.zeta.hr.management.utils;

import static org.zeta.hr.management.constants.JdbcConstants.PASSWORD;
import static org.zeta.hr.management.constants.JdbcConstants.URL;
import static org.zeta.hr.management.constants.JdbcConstants.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

  public static Connection getConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public static void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void closeStatement(Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void closeResultSet(ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
