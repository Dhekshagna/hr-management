package org.zeta.hr.management.constants;

public class EmployeeConstants {
  public static final String INSERT_EMPLOYEE =
      "INSERT INTO employee (id, first_name, last_name, role, reports_to, leave_balance, sick_leaves, paid_leaves, email_id, phone, city, locality, state, pin_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";//HR
  public static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee WHERE id = ?";//Employee
  public static final String UPDATE_EMPLOYEE =
      "UPDATE employee SET first_name = ?, last_name = ?, role = ?, reports_to = ?, leave_balance = ?, sick_leaves = ?, paid_leaves = ?, email_id = ?, phone = ?, city = ?, locality = ?, state = ?, pin_code = ? WHERE id = ?";//HR and Employee
  public static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";//HR
  public static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";//HR
  public static final String SELECT_EMPLOYEE_BY_REPORTS_TO =
      "SELECT * FROM employee WHERE reports_to = ?";//Manager
  public static final String UPDATE_EMPLOYEE_REPORTS_TO =
      "UPDATE employee SET reports_to = ? WHERE id = ?";//HR
  public static final String UPDATE_EMPLOYEE_LEAVE_BALANCE_SICK =
      "UPDATE employee SET sick_leaves = sick_leaves - ?, leave_balance = leave_balance - ? WHERE id = ?";//internal usage
  public static final String UPDATE_EMPLOYEE_LEAVE_BALANCE_PAID =
        "UPDATE employee SET paid_leaves = paid_leaves - ?, leave_balance = leave_balance - ? WHERE id = ?";//internal usage

}
