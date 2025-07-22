package org.zeta.hr.management.constants;

import static org.zeta.hr.management.constants.EmployeeConstants.SELECT_EMPLOYEE_BY_REPORTS_TO;

public class LeaveConstants {
    public static final String INSERT_LEAVE = "INSERT INTO leave_application (employee_id, leave_type, start_date, end_date, reason) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_LEAVE_STATUS_QUERY = "UPDATE leave_application SET status = ? WHERE leave_id = ?";
    public static final String SELECT_LEAVE_BY_ID_QUERY_IF_UNDER_THIS_EMPLOYEE = "SELECT * FROM leave_application WHERE leave_id = ? AND employee_id IN ("+ SELECT_EMPLOYEE_BY_REPORTS_TO +")";
    public static final String SELECT_LEAVES_BY_UNDER_AN_EMPLOYEE_QUERY = "SELECT * FROM leave_application WHERE employee_id IN ("+ SELECT_EMPLOYEE_BY_REPORTS_TO +")";
    public static final String SELECT_LEAVES_BY_UNDER_AN_EMPLOYEE_QUERY_PENDING_STATUS = "SELECT * FROM leave_application WHERE employee_id IN ("+ SELECT_EMPLOYEE_BY_REPORTS_TO +") AND status = 'APPLIED'";
    public static final String DELETE_LEAVE_BY_ID_QUERY = "DELETE FROM leave_application WHERE leave_id = ?";
    public static final String SELECT_LEAVE_BY_EMPLOYEE_ID_QUERY = "SELECT * FROM leave_application WHERE employee_id = ?";
}
