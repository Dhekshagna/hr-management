package org.zeta.hr.management.service;

public interface LeaveService {
  void applyLeave(
      int employeeId, String leaveType, String startDate, String endDate, String reason);

  void updateLeaveStatus(int leaveId, String status);

  void cancelLeave(int leaveId);

  void viewLeaveDetails(int leaveId, String employeeId);

  void viewLeavesByEmployee(String employeeId);

  void viewLeavesUnderManager(String managerId);

  void viewPendingLeavesUnderManager(String managerId);
}
