package org.zeta.hr.management.service.impl;

import org.zeta.hr.management.service.LeaveService;

public class LeaveServiceImpl implements LeaveService {

  @Override
  public void applyLeave(
      int employeeId, String leaveType, String startDate, String endDate, String reason) {
    // Implementation for applying leave
  }

  @Override
  public void updateLeaveStatus(int leaveId, String status) {
    // Implementation for updating leave status
  }

  @Override
  public void cancelLeave(int leaveId) {
    // Implementation for cancelling leave
  }

  @Override
  public void viewLeaveDetails(int leaveId, String employeeId) {
    // Implementation for viewing leave details
  }

  @Override
  public void viewLeavesByEmployee(String employeeId) {
    // Implementation for viewing leaves by employee
  }

  @Override
  public void viewLeavesUnderManager(String managerId) {
    // Implementation for viewing leaves under a manager
  }

  @Override
  public void viewPendingLeavesUnderManager(String managerId) {
    // Implementation for viewing pending leaves under a manager
  }
}
