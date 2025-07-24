package org.zeta.hr.management.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.zeta.hr.management.dao.LeaveDAO;
import org.zeta.hr.management.entity.Employee;
import org.zeta.hr.management.entity.Leave;
import org.zeta.hr.management.enums.EmployeeRole;
import org.zeta.hr.management.enums.LeaveStatus;
import org.zeta.hr.management.enums.LeaveType;
import org.zeta.hr.management.exception.ResourceNotFoundException;
import org.zeta.hr.management.service.EmployeeService;
import org.zeta.hr.management.service.LeaveService;

public class LeaveServiceImpl implements LeaveService {
  LeaveDAO leaveDAO = new LeaveDAO();
  EmployeeService employeeService = new EmployeeServiceImpl();

  @Override
  public void applyLeave(
      int employeeId, LeaveType leaveType, String startDate, String endDate, String reason) {
    if (leaveDAO.applyLeave(employeeId, leaveType, startDate, endDate, reason)) {
      System.out.println("Leave application submitted successfully.");
    } else {
      System.out.println("Failed to submit leave application. Please try again.");
    }
  }

  @Override
  public void updateLeaveStatus(int leaveId, LeaveStatus status, int managerId) {

    Leave leave = leaveDAO.getLeaveById(leaveId, managerId);
    Employee employee = employeeService.viewEmployee(leave.getEmployeeId());

    if (employee.getReportsTo() != managerId) {
      throw new ResourceNotFoundException(
          "Employee with ID "
              + managerId
              + " is not the manager of this Employee. Hence cannot APPROVE/REJECT the leave.");
    }

    Employee manager = employeeService.viewEmployee(managerId);
    if (leave == null) {
      throw new ResourceNotFoundException("Leave not found for the given ID and manager.");
    }
    if (manager == null
        || (manager.getRole() != EmployeeRole.MANAGER && manager.getRole() != EmployeeRole.CEO)) {
      throw new ResourceNotFoundException("Manager not found for the given ID.");
    }

    if (isEligibleForLeave(employee, leave)) {
      if (leaveDAO.updateLeaveStatus(leaveId, status)) {
        if (status.equals(LeaveStatus.APPROVED)) {
          updateLeaveBalance(leave.employeeId, leave.leaveType, leave.startDate, leave.endDate);
          System.out.println(
              "Leave approved successfully for Employee ID: " + leave.getEmployeeId());
        } else if (status.equals(LeaveStatus.REJECTED)) {
          System.out.println(
              "Leave rejected successfully for Employee ID: " + leave.getEmployeeId());
        }
      } else {
        System.out.println("Failed to update leave status. Please try again.");
      }
    } else {
      System.out.println(
          "Leave status cannot be updated further or the employee is not eligible for leave.");
    }
  }

  @Override
  public void cancelLeave(int leaveId, int employeeId) {
    Leave leave = leaveDAO.getLeaveById(leaveId, employeeId);
    for (Leave l : viewLeavesByEmployee(employeeId)) {
      System.out.println(l);
    }
    if (leave == null) {
      throw new ResourceNotFoundException("Leave not found for the given ID.");
    }

    if (leave.getStatus() != LeaveStatus.APPLIED) {
      throw new ResourceNotFoundException(
          "Leave cannot be cancelled as it is already APPROVED/REJECTED.");
    }
    if (leaveDAO.deleteLeaveById(leaveId, employeeId)) {
      System.out.println("Leave cancelled successfully.");
    } else {
      System.out.println("Failed to cancel leave. Please try again.");
    }
  }

  @Override
  public Leave viewLeaveDetails(int leaveId, int employeeId) {
    Employee employee = employeeService.viewEmployee(employeeId);
    if (employee == null) {
      throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
    }
    Leave leave = leaveDAO.getLeaveById(leaveId, employeeId);
    if (leave == null) {
      throw new ResourceNotFoundException("Leave not found with ID: " + leaveId);
    }
    return leave;
  }

  @Override
  public List<Leave> viewLeavesByEmployee(int employeeId) {
    Employee employee = employeeService.viewEmployee(employeeId);
    if (employee == null) {
      throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
    }
    List<Leave> leaves = leaveDAO.getLeavesByEmployeeId(employeeId);
    if (leaves.isEmpty()) {
      throw new ResourceNotFoundException("No leaves found for employee with ID: " + employeeId);
    }
    return leaves;
  }

  @Override
  public List<Leave> viewLeavesUnderManager(int managerId) {
    List<Leave> leaves = leaveDAO.getLeavesUnderManager(managerId);
    if (leaves.isEmpty()) {
      throw new ResourceNotFoundException("No leaves found under manager with ID: " + managerId);
    }
    return leaves;
  }

  @Override
  public List<Leave> viewPendingLeavesUnderManager(int managerId) {
    System.out.println("Pending leaves under manager with ID: " + managerId);
    Employee manager = employeeService.viewEmployee(managerId);
    if (manager == null
        || (manager.getRole() != EmployeeRole.MANAGER && manager.getRole() != EmployeeRole.CEO)) {
      throw new ResourceNotFoundException("Manager not found with ID: " + managerId);
    }
    List<Leave> pendingLeaves = leaveDAO.getLeavesByStatus(managerId);
    if (pendingLeaves.isEmpty()) {
      throw new ResourceNotFoundException(
          "No pending leaves found under manager with ID: " + managerId);
    }
    return pendingLeaves;
  }

  private void updateLeaveBalance(
      int employeeId, LeaveType leaveType, LocalDate startDate, LocalDate endDate) {
    if (leaveType.equals(LeaveType.SICK)) {
      employeeService.updateLeaveBalanceSick(employeeId, noOfDays(startDate, endDate));
    } else if (leaveType.equals(LeaveType.PAID)) {
      employeeService.updateLeaveBalancePaid(employeeId, noOfDays(startDate, endDate));
    } else {
      throw new IllegalArgumentException("Invalid leave type: " + leaveType);
    }
    System.out.println(
        "Leave balance updated for Employee ID: " + employeeId + " for leave type: " + leaveType);
  }

  private boolean isEligibleForLeave(Employee employee, Leave leave) {
    if (leave.getLeaveType().equals(LeaveType.PAID)) {
      return employee.getPaidLeaves() >= noOfDays(leave.getStartDate(), leave.getEndDate())
          && leave.getStatus().equals(LeaveStatus.APPLIED);
    } else {
      return employee.getSickLeaves() >= noOfDays(leave.getStartDate(), leave.getEndDate())
          && leave.getStatus().equals(LeaveStatus.APPLIED);
    }
  }

  private int noOfDays(LocalDate startDate, LocalDate endDate) {
    return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
  }
}
