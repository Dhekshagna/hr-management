package org.zeta.hr.management.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.zeta.hr.management.constants.EmployeeConstants;
import org.zeta.hr.management.entity.Employee;
import org.zeta.hr.management.enums.EmployeeRole;
import org.zeta.hr.management.utils.DatabaseConnection;

public class EmployeeDAO {

  public EmployeeDAO() {
    insertCEO();
    insertHR();
  }

  public boolean insertEmployee(Employee employee) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int rowsInserted = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.INSERT_EMPLOYEE);
      preparedStatement.setInt(1, employee.getId());
      preparedStatement.setString(2, employee.getFirstName());
      preparedStatement.setString(3, employee.getLastName());
      preparedStatement.setString(4, employee.getRole().toString());
      preparedStatement.setInt(5, employee.getReportsTo());
      preparedStatement.setInt(6, employee.getLeaveBalance());
      preparedStatement.setInt(7, employee.getSickLeaves());
      preparedStatement.setInt(8, employee.getPaidLeaves());
      preparedStatement.setString(9, employee.getEmailId());
      preparedStatement.setString(10, employee.getPhone());
      preparedStatement.setString(11, employee.getCity());
      preparedStatement.setString(12, employee.getLocality());
      preparedStatement.setString(13, employee.getState());
      preparedStatement.setString(14, employee.getPinCode());
      rowsInserted = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return rowsInserted == 1;
  }

  public Employee getEmployeeById(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Employee employee = null;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.SELECT_EMPLOYEE_BY_ID);
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        employee =
            new Employee(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                EmployeeRole.valueOf(resultSet.getString("role")),
                resultSet.getInt("reports_to"),
                resultSet.getString("email_id"),
                resultSet.getString("phone"),
                resultSet.getString("city"),
                resultSet.getString("locality"),
                resultSet.getString("state"),
                resultSet.getString("pin_code"));
        employee.setId(resultSet.getInt("id"));
        employee.setLeaveBalance(resultSet.getInt("leave_balance"));
        employee.setSickLeaves(resultSet.getInt("sick_leaves"));
        employee.setPaidLeaves(resultSet.getInt("paid_leaves"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return employee;
  }

  public List<Employee> getAllEmployees() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Employee> employees = new ArrayList<>();
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.SELECT_ALL_EMPLOYEES);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Employee employee =
            new Employee(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                EmployeeRole.valueOf(resultSet.getString("role")),
                resultSet.getInt("reports_to"),
                resultSet.getString("email_id"),
                resultSet.getString("phone"),
                resultSet.getString("city"),
                resultSet.getString("locality"),
                resultSet.getString("state"),
                resultSet.getString("pin_code"));
        employee.setId(resultSet.getInt("id"));
        employee.setLeaveBalance(resultSet.getInt("leave_balance"));
        employee.setSickLeaves(resultSet.getInt("sick_leaves"));
        employee.setPaidLeaves(resultSet.getInt("paid_leaves"));
        employees.add(employee);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return employees;
  }

  public boolean updateEmployee(Employee employee) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int rowsUpdated = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.UPDATE_EMPLOYEE);
      preparedStatement.setString(1, employee.getFirstName());
      preparedStatement.setString(2, employee.getLastName());
      preparedStatement.setString(3, employee.getRole().toString());
      preparedStatement.setInt(4, employee.getReportsTo());
      preparedStatement.setInt(5, employee.getLeaveBalance());
      preparedStatement.setInt(6, employee.getSickLeaves());
      preparedStatement.setInt(7, employee.getPaidLeaves());
      preparedStatement.setString(8, employee.getEmailId());
      preparedStatement.setString(9, employee.getPhone());
      preparedStatement.setString(10, employee.getCity());
      preparedStatement.setString(11, employee.getLocality());
      preparedStatement.setString(12, employee.getState());
      preparedStatement.setString(13, employee.getPinCode());
      preparedStatement.setInt(14, employee.getId());
      rowsUpdated = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return rowsUpdated == 1;
  }

  public boolean updateEmployeeReportsTo(int employeeId, int reportsTo) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int rowsUpdated = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.UPDATE_EMPLOYEE_REPORTS_TO);
      preparedStatement.setInt(1, reportsTo);
      preparedStatement.setInt(2, employeeId);
      rowsUpdated = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return rowsUpdated == 1;
  }

  public List<Employee> getAllManagers() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Employee> managers = new ArrayList<>();
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement =
          connection.prepareStatement(EmployeeConstants.SELECT_EMPLOYEE_BY_ROLE_MANAGER);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Employee manager =
            new Employee(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                EmployeeRole.valueOf(resultSet.getString("role")),
                resultSet.getInt("reports_to"),
                resultSet.getString("email_id"),
                resultSet.getString("phone"),
                resultSet.getString("city"),
                resultSet.getString("locality"),
                resultSet.getString("state"),
                resultSet.getString("pin_code"));
        manager.setId(resultSet.getInt("id"));
        manager.setLeaveBalance(resultSet.getInt("leave_balance"));
        manager.setSickLeaves(resultSet.getInt("sick_leaves"));
        manager.setPaidLeaves(resultSet.getInt("paid_leaves"));
        managers.add(manager);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return managers;
  }

  public Employee getEmployeeByEmail(String email) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Employee employee = null;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.SELECT_EMPLOYEE_BY_EMAIL);
      preparedStatement.setString(1, email);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        employee =
            new Employee(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                EmployeeRole.valueOf(resultSet.getString("role")),
                resultSet.getInt("reports_to"),
                resultSet.getString("email_id"),
                resultSet.getString("phone"),
                resultSet.getString("city"),
                resultSet.getString("locality"),
                resultSet.getString("state"),
                resultSet.getString("pin_code"));
        employee.setId(resultSet.getInt("id"));
        employee.setLeaveBalance(resultSet.getInt("leave_balance"));
        employee.setSickLeaves(resultSet.getInt("sick_leaves"));
        employee.setPaidLeaves(resultSet.getInt("paid_leaves"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return employee;
  }

  public Employee getEmployeeByPhone(String phone) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Employee employee = null;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.SELECT_EMPLOYEE_BY_PHONE);
      preparedStatement.setString(1, phone);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        employee =
            new Employee(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                EmployeeRole.valueOf(resultSet.getString("role")),
                resultSet.getInt("reports_to"),
                resultSet.getString("email_id"),
                resultSet.getString("phone"),
                resultSet.getString("city"),
                resultSet.getString("locality"),
                resultSet.getString("state"),
                resultSet.getString("pin_code"));
        employee.setId(resultSet.getInt("id"));
        employee.setLeaveBalance(resultSet.getInt("leave_balance"));
        employee.setSickLeaves(resultSet.getInt("sick_leaves"));
        employee.setPaidLeaves(resultSet.getInt("paid_leaves"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return employee;
  }

  public boolean deleteEmployee(int id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int rowsDeleted = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.DELETE_EMPLOYEE);
      preparedStatement.setInt(1, id);
      rowsDeleted = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return rowsDeleted == 1;
  }

  public List<Employee> getEmployeesByManagerId(int managerId) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Employee> employees = new ArrayList<>();
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement =
          connection.prepareStatement(EmployeeConstants.SELECT_EMPLOYEE_BY_REPORTS_TO);
      preparedStatement.setInt(1, managerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Employee employee =
            new Employee(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                EmployeeRole.valueOf(resultSet.getString("role")),
                resultSet.getInt("reports_to"),
                resultSet.getString("email_id"),
                resultSet.getString("phone"),
                resultSet.getString("city"),
                resultSet.getString("locality"),
                resultSet.getString("state"),
                resultSet.getString("pin_code"));
        employee.setId(resultSet.getInt("id"));
        employee.setLeaveBalance(resultSet.getInt("leave_balance"));
        employee.setSickLeaves(resultSet.getInt("sick_leaves"));
        employee.setPaidLeaves(resultSet.getInt("paid_leaves"));
        employees.add(employee);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return employees;
  }

  public boolean updateEmployeeLeaveBalanceSick(
      int employeeId, int sickLeavesRemaining, int leaveBalanceRemaining) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int rowsUpdated = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement =
          connection.prepareStatement(EmployeeConstants.UPDATE_EMPLOYEE_LEAVE_BALANCE_SICK);
      preparedStatement.setInt(1, sickLeavesRemaining);
      preparedStatement.setInt(2, leaveBalanceRemaining);
      preparedStatement.setInt(3, employeeId);
      rowsUpdated = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return rowsUpdated == 1;
  }

  public boolean updateEmployeeLeaveBalancePaid(
      int employeeId, int paidLeavesRemaining, int leaveBalanceRemaining) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int rowsUpdated = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement =
          connection.prepareStatement(EmployeeConstants.UPDATE_EMPLOYEE_LEAVE_BALANCE_PAID);
      preparedStatement.setInt(1, paidLeavesRemaining);
      preparedStatement.setInt(2, leaveBalanceRemaining);
      preparedStatement.setInt(3, employeeId);
      rowsUpdated = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return rowsUpdated == 1;
  }

  public int countRowsInserted() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int count = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.COUNT_ROWS_INSERTED);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        count = resultSet.getInt(1);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return count;
  }

  public void insertCEO() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.INSERT_CEO_IF_NOT_EXISTS);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
  }

  public void insertHR() {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(EmployeeConstants.INSERT_HR_IF_NOT_EXISTS);
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
  }
}
