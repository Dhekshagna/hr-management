package org.zeta.hr.management.service;

import java.util.List;

import org.zeta.hr.management.entity.Leave;
import org.zeta.hr.management.enums.LeaveStatus;
import org.zeta.hr.management.enums.LeaveType;

public interface LeaveService {
  void applyLeave(
      int employeeId, LeaveType leaveType, String startDate, String endDate, String reason);

  void updateLeaveStatus(int leaveId, LeaveStatus status, int managerId);

  void cancelLeave(int leaveId, int employeeId);

  Leave viewLeaveDetails(int leaveId, int employeeId);

  List<Leave> viewLeavesByEmployee(int employeeId);

  List<Leave> viewLeavesUnderManager(int managerId);

  List<Leave> viewPendingLeavesUnderManager(int managerId);
}
