package com.oneframe.cucumber.oneframebase.utils.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.oneframe.cucumber.oneframebase.utils.LogPrinter;

public class MySqlDbUtils {

  private MySqlDbUtils() {
    throw new AssertionError("Cant instantiate " + MySqlDbUtils.class.getSimpleName());
  }

  private static final String MYSQL_DB_HOST = MySqlBeans.getMySqlDbHostName();
  private static final String MYSQL_DB_PORT = MySqlBeans.getMysqlDbPortNumber();
  private static final String MYSQL_DB_NAME = MySqlBeans.getMysqlDbName();
  private static final String MYSQL_DB_USERNAME = MySqlBeans.getMysqlDbUserName();
  private static final String MYSQL_DB_PASSWORD = MySqlBeans.getMysqlDbUserPassword();
  private static final String MYSQL_DB_TABLE = MySqlBeans.getMysqlDbTableName();

  private static final String MYSQL_DB_CONNECTION_STRING =
      "jdbc:mysql://" + MYSQL_DB_HOST + ":" + MYSQL_DB_PORT + "/" + MYSQL_DB_NAME;

  private static final String MYSQL_PREPARED_QUERY = "insert into " + MYSQL_DB_NAME + "."
      + MYSQL_DB_TABLE + "(applicationName,testCaseName,executionTime,executionStatus,machineIP) "
      + "values (?,?,?,?,?)";

  private static Connection makeConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(MYSQL_DB_CONNECTION_STRING, MYSQL_DB_USERNAME,
          MYSQL_DB_PASSWORD);
    } catch (SQLException e) {
      LogPrinter.printLog("Unable to create Connection with the MySql DB");
      LogPrinter.printLog("Connection String is " + MYSQL_DB_CONNECTION_STRING);
      LogPrinter.printLog("Error found " + e.getMessage());
    }
    return connection;
  }

  /**
   * Insert automation execution data like, application name, test case name, time taken for an
   * execution, status of the execution and IP address of the machine where execution happened.
   *
   * @param applicationName - Name of the application whose testing is happening.
   * @param testCaseName - name of the test cases.
   * @param executionTime - time taken for an execution.
   * @param executionStatus - status as Pass or Fail
   * @param machineIP - IP address of the machine.
   * @author Sudheer.Singh
   */
  public static void insertAutomationExecutionData(String applicationName, String testCaseName,
      int executionTime, String executionStatus, String machineIP) {
    PreparedStatement statement = null;
    try {
      final Connection mysqlConnection = makeConnection();
      if (mysqlConnection == null) {
        throw new SQLException("Connection is not established with MySql DB");
      }
      statement = mysqlConnection.prepareStatement(MYSQL_PREPARED_QUERY);
      statement.setString(1, applicationName);
      statement.setString(2, testCaseName);
      statement.setInt(3, executionTime);
      statement.setString(4, executionStatus);
      statement.setString(5, machineIP);
      int rowsAffected = statement.executeUpdate();
      LogPrinter.printLog("Added " + rowsAffected + " rows into " + MYSQL_DB_TABLE);
    } catch (SQLException e) {
      LogPrinter.printLog(
          "Unable to insert data into " + MYSQL_DB_TABLE + " Error found is " + e.getMessage());
    } finally {
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException e) {
          LogPrinter.printLog("Unable to close PreparedStatement Instance " + e.getMessage());
        }
      }
    }
  }
}
