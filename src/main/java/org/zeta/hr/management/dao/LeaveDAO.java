package org.zeta.hr.management.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.zeta.hr.management.constants.LeaveConstants;
import org.zeta.hr.management.entity.Leave;
import org.zeta.hr.management.enums.LeaveStatus;
import org.zeta.hr.management.enums.LeaveType;
import org.zeta.hr.management.utils.DatabaseConnection;

public class LeaveDAO {
  public boolean applyLeave(
      int employeeId, LeaveType leaveType, String startDate, String endDate, String reason) {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    int rowsInserted = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(LeaveConstants.INSERT_LEAVE);
      preparedStatement.setInt(1, employeeId);
      preparedStatement.setString(2, leaveType.toString());
      preparedStatement.setDate(3, Date.valueOf(startDate));
      preparedStatement.setDate(4, Date.valueOf(endDate));
      preparedStatement.setString(5, reason);
      rowsInserted = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return rowsInserted == 1;
  }

  public boolean updateLeaveStatus(int leaveId, LeaveStatus status) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int rowsUpdated = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(LeaveConstants.UPDATE_LEAVE_STATUS_QUERY);
      preparedStatement.setString(1, status.toString());
      preparedStatement.setInt(2, leaveId);
      rowsUpdated = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return rowsUpdated == 1;
  }

  // get leave by if the leave is under this employee
  public Leave getLeaveById(int leaveId, int employeeId) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Leave leave = null;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement =
          connection.prepareStatement(
              LeaveConstants.SELECT_LEAVE_BY_ID_QUERY_IF_UNDER_THIS_EMPLOYEE);
      preparedStatement.setInt(1, leaveId);
      preparedStatement.setInt(2, employeeId);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        leave =
            new Leave(
                resultSet.getInt(1),
                resultSet.getInt(2),
                LeaveType.valueOf(resultSet.getString(3)),
                resultSet.getDate(4).toLocalDate(),
                resultSet.getDate(5).toLocalDate(),
                LeaveStatus.valueOf(resultSet.getString(6)),
                resultSet.getString(7));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return leave;
  }

  // get leaves by employee id
  public List<Leave> getLeavesByEmployeeId(int employeeId) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Leave> leaves = new ArrayList<>();
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement =
          connection.prepareStatement(LeaveConstants.SELECT_LEAVE_BY_EMPLOYEE_ID_QUERY);
      preparedStatement.setInt(1, employeeId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        leaves.add(
            new Leave(
                resultSet.getInt(1),
                resultSet.getInt(2),
                LeaveType.valueOf(resultSet.getString(3)),
                resultSet.getDate(4).toLocalDate(),
                resultSet.getDate(5).toLocalDate(),
                LeaveStatus.valueOf(resultSet.getString(6)),
                resultSet.getString(7)));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return leaves;
  }

  // get all leaves under an employee (manager)
  public List<Leave> getLeavesUnderManager(int managerId) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Leave> leaves = new ArrayList<>();
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement =
          connection.prepareStatement(LeaveConstants.SELECT_LEAVES_BY_UNDER_AN_EMPLOYEE_QUERY);
      preparedStatement.setInt(1, managerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        leaves.add(
            new Leave(
                resultSet.getInt(1),
                resultSet.getInt(2),
                LeaveType.valueOf(resultSet.getString(3)),
                resultSet.getDate(4).toLocalDate(),
                resultSet.getDate(5).toLocalDate(),
                LeaveStatus.valueOf(resultSet.getString(6)),
                resultSet.getString(7)));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return leaves;
  }

  // get all leaves under an employee (manager) with pending status
  public List<Leave> getLeavesByStatus(int managerId) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Leave> leaves = new ArrayList<>();
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement =
          connection.prepareStatement(
              LeaveConstants.SELECT_LEAVES_BY_UNDER_AN_EMPLOYEE_QUERY_PENDING_STATUS);
      preparedStatement.setInt(1, managerId);
      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        leaves.add(
            new Leave(
                resultSet.getInt(1),
                resultSet.getInt(2),
                LeaveType.valueOf(resultSet.getString(3)),
                resultSet.getDate(4).toLocalDate(),
                resultSet.getDate(5).toLocalDate(),
                LeaveStatus.valueOf(resultSet.getString(6)),
                resultSet.getString(7)));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeResultSet(resultSet);
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return leaves;
  }

  // delete leave by id
  public boolean deleteLeaveById(int leaveId, int employeeId) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    int rowsDeleted = 0;
    try {
      connection = DatabaseConnection.getConnection();
      preparedStatement = connection.prepareStatement(LeaveConstants.DELETE_LEAVE_BY_ID_QUERY);
      preparedStatement.setInt(1, leaveId);
      preparedStatement.setInt(2, employeeId);
      rowsDeleted = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      DatabaseConnection.closeStatement(preparedStatement);
      DatabaseConnection.closeConnection(connection);
    }
    return rowsDeleted == 1;
  }
}
