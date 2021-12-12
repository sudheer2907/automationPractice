package com.oneframe.cucumber.oneframebase.utils.database;

public class MySqlBeans {

  private String applicationName;
  private String testCaseName;
  private String executionStatus;
  private String machineIP;
  private int executionTime;
  private static MySqlBeans mySqlBeans;

  /**
   * Database connections properties.
   */
  private String mysqlDbHostName;
  private String mysqlDbPortNumber;
  private String mysqlDbName;
  private String mysqlDbUserName;
  private String mysqlDbUserPassword;
  private String mysqlDbTable;

  /**
   * Private no argument constructor for FilesBeans restricting instance creation.
   */
  private MySqlBeans() {}

  /**
   * Initialize the Bean static singleton instance, this method is based on Singleton design
   * patterns which helps in initializing this class only one times.
   */
  private static void initMySqlBeans() {
    if (mySqlBeans == null) {
      mySqlBeans = new MySqlBeans();
    }
  }

  /**
   * Set the application name which will be inserted into database.
   *
   * @param applictaionName - name of the application whose testing is happening.
   */
  public static void setApplicationName(String applictaionName) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.applicationName = applictaionName;
  }

  /**
   * Get the application name whose value will be inserted into the mysql database.
   *
   * @return - name of the application into String format.
   */
  public static String getApplicationName() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.applicationName;
  }

  /**
   * Set the test case name which will be inserted into database.
   *
   * @param testCaseName - name of the test cases whose execution is happening.
   */
  public static void setTestCaseName(String testCaseName) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.testCaseName = testCaseName;
  }

  /**
   * Get the test Case name whose value will be inserted into the mysql database.
   *
   * @return - name of the test case.
   */
  public static String getTestCaseName() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.testCaseName;
  }

  /**
   * Set execution time, it will be the time taken by any test cases to complete its execution.
   *
   * @param executionTime - execution time in seconds.
   */
  public static void setExecutionTime(int executionTime) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.executionTime = executionTime;
  }

  /**
   * Get total time of execution.
   *
   * @return - total execution time in seconds.
   */
  public static int getExecutionTime() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.executionTime;
  }

  /**
   * Set execution status, it will be either passed/failed/skipped.
   *
   * @param executionStatus - Pass execution status, either Passed/Failed/Skipped
   */
  public static void setExecutionStatus(String executionStatus) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.executionStatus = executionStatus;
  }

  /**
   * Get execution status.
   *
   * @return - either Passed/Failed or Skipped.
   */
  public static String getExecutionStatus() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.executionStatus;
  }

  /**
   * Set machine ip address.
   *
   * @param machineIP - machine ip address in string format.
   */
  public static void setMachineIpAddress(String machineIP) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.machineIP = machineIP;
  }

  /**
   * Get machine ip address.
   *
   * @return - IP adrress of the machine.
   */
  public static String getMachineIp() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.machineIP;
  }

  /**
   * Set mysql db host name.
   *
   * @param mysqlDbHostName - db host name.
   */
  public static void setMySqlDbHostName(String mysqlDbHostName) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.mysqlDbHostName = mysqlDbHostName;
  }

  /**
   * Get mysql db host name.
   *
   * @return - db host name.
   */
  public static String getMySqlDbHostName() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.mysqlDbHostName;
  }

  /**
   * Set mysql db port number.
   *
   * @param mysqlDbPortNumber - mysql db port number.
   */
  public static void setMysqlDbPortNumber(String mysqlDbPortNumber) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.mysqlDbPortNumber = mysqlDbPortNumber;
  }

  /**
   * Get mysql db port number.
   *
   * @return - db port number
   */
  public static String getMysqlDbPortNumber() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.mysqlDbPortNumber;
  }

  /**
   * Set mysql db name.
   *
   * @param mysqlDbName - mysql db name.
   */
  public static void setMysqlDbName(String mysqlDbName) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.mysqlDbName = mysqlDbName;
  }

  /**
   * Get mysql db name.
   *
   * @return - mysql db name.
   */
  public static String getMysqlDbName() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.mysqlDbName;
  }

  /**
   * Set mysql db username.
   *
   * @param mysqlDbUserName - mysql db username.
   */
  public static void setMysqlDbUserName(String mysqlDbUserName) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.mysqlDbUserName = mysqlDbUserName;
  }

  /**
   * Get mysql user name.
   *
   * @return - db username.
   */
  public static String getMysqlDbUserName() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.mysqlDbUserName;
  }

  /**
   * Get mysql db user password.
   *
   * @param mysqlDbUserPassword - mysql db password.
   */
  public static void setMysqlDbUserPassword(String mysqlDbUserPassword) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.mysqlDbUserPassword = mysqlDbUserPassword;
  }

  /**
   * Get mysql db user password.
   *
   * @return - db password
   */
  public static String getMysqlDbUserPassword() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.mysqlDbUserPassword;
  }

  /**
   * Set mysql db table name.
   *
   * @param mysqlDbTable - name of the table.
   */
  public static void setMysqlDbTableName(String mysqlDbTable) {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    mySqlBeans.mysqlDbTable = mysqlDbTable;
  }

  /**
   * Get mysql db table name.
   *
   * @return - name of the table.
   */
  public static String getMysqlDbTableName() {
    if (mySqlBeans == null) {
      initMySqlBeans();
    }
    return mySqlBeans.mysqlDbTable;
  }
}
