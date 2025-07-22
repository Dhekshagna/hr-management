package org.zeta.hr.management.entity;

import org.zeta.hr.management.enums.LeaveStatus;
import org.zeta.hr.management.enums.LeaveType;

import java.time.LocalDate;

public class Leave {
    public int leaveId;
    public int employeeId;
    public LeaveType leaveType;
    public LocalDate startDate;
    public LocalDate endDate;
    public LeaveStatus status;
    public String reason;

    public Leave (int leaveId, int employeeId, LeaveType leaveType, LocalDate startDate, LocalDate endDate, LeaveStatus status, String reason) {
        this.leaveId = leaveId;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.reason = reason;
    }

    public int getLeaveId () {
        return leaveId;
    }

    public void setLeaveId (int leaveId) {
        this.leaveId = leaveId;
    }

    public int getEmployeeId () {
        return employeeId;
    }

    public void setEmployeeId (int employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveType getLeaveType () {
        return leaveType;
    }

    public void setLeaveType (LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getStartDate () {
        return startDate;
    }

    public void setStartDate (LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate () {
        return endDate;
    }

    public void setEndDate (LocalDate endDate) {
        this.endDate = endDate;
    }

    public LeaveStatus getStatus () {
        return status;
    }

    public void setStatus (LeaveStatus status) {
        this.status = status;
    }

    public String getReason () {
        return reason;
    }

    public void setReason (String reason) {
        this.reason = reason;
        }

    @Override
    public String toString() {
        return String.format("""
        📝 Leave ID: %d
        👤 Employee ID: %d
        🏷️ Type: %s
        📅 Start: %s
        📅 End: %s
        🔖 Status: %s
        💬 Reason: %s
        """,
            leaveId, employeeId, leaveType, startDate, endDate, status, reason
        );
    }
}

