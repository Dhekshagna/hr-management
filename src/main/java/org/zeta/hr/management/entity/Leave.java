package org.zeta.hr.management.entity;

import java.time.LocalDate;

public class Leave {
    public int leaveId;
    public int employeeId;
    public String leaveType;
    public LocalDate startDate, endDate;
    public String status = "APPLIED";

    public Leave(int employeeId, String leaveType, LocalDate startDate, LocalDate endDate) {
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getLeaveType () {
        return leaveType;
    }

    public void setLeaveType (String leaveType) {
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

    public String getStatus () {
        return status;
    }

    public void setStatus (String status) {
        this.status = status;
    }
}

