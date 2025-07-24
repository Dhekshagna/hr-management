package org.zeta.hr.management.constants;

public class EmployeeConstants {
  public static final String INSERT_EMPLOYEE =
      "INSERT INTO employee (id, first_name, last_name, role, reports_to, leave_balance,"
          + " sick_leaves, paid_leaves, email_id, phone, city, locality, state, pin_code) VALUES"
          + " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  public static final String SELECT_EMPLOYEE_BY_ID =
      "SELECT * FROM employee WHERE id = ?";
  public static final String UPDATE_EMPLOYEE =
      "UPDATE employee SET first_name = ?, last_name = ?, role = ?, reports_to = ?, leave_balance ="
          + " ?, sick_leaves = ?, paid_leaves = ?, email_id = ?, phone = ?, city = ?, locality = ?,"
          + " state = ?, pin_code = ? WHERE id = ?";
  public static final String DELETE_EMPLOYEE = "DELETE FROM employee WHERE id = ?";
  public static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM employee";
  public static final String SELECT_EMPLOYEE_BY_REPORTS_TO =
      "SELECT id FROM employee WHERE reports_to = ?";
  public static final String SELECT_EMPLOYEE_BY_REPORTS_TO_ALL_COLUMNS =
      "SELECT * FROM employee WHERE reports_to = ?";
  public static final String UPDATE_EMPLOYEE_REPORTS_TO =
      "UPDATE employee SET reports_to = ? WHERE id = ?";
  public static final String UPDATE_EMPLOYEE_LEAVE_BALANCE_SICK =
      "UPDATE employee SET sick_leaves = ?, leave_balance = ? WHERE" + " id = ?";
  public static final String UPDATE_EMPLOYEE_LEAVE_BALANCE_PAID =
      "UPDATE employee SET paid_leaves = ?, leave_balance = ? WHERE" + " id = ?";
  public static final String COUNT_ROWS_INSERTED =
      "SELECT COUNT(*) FROM employee";
  public static final String INSERT_CEO_IF_NOT_EXISTS =
      "INSERT INTO employee (id, first_name, last_name, role, reports_to, leave_balance,"
          + " sick_leaves, paid_leaves, email_id, phone, city, locality, state, pin_code)"
          + " SELECT 0, 'John', 'Doe', 'CEO', -1, 22, 12, 10,"
          + " 'ceo@company.com', '1234567890', 'Metropolis', 'Central', 'Delhi', '900010'"
          + " WHERE NOT EXISTS (SELECT 1 FROM employee WHERE id = 0)";
  public static final String INSERT_HR_IF_NOT_EXISTS =
      "INSERT INTO employee (id, first_name, last_name, role, reports_to, leave_balance,"
          + " sick_leaves, paid_leaves, email_id, phone, city, locality, state, pin_code) SELECT 1,"
          + " 'Dhekshagna', 'Goli', 'HR', 0, 22, 12, 10, 'dhekshagna@gmail.com', '8097635734',"
          + " 'Hyderabad', 'Kukatpally', 'Telangana', '500072' WHERE NOT EXISTS (SELECT 1 FROM"
          + " employee WHERE role = 'HR')";
  public static final String SELECT_EMPLOYEE_BY_EMAIL =
      "SELECT * FROM employee WHERE email_id = ?";
  public static final String SELECT_EMPLOYEE_BY_PHONE =
      "SELECT * FROM employee WHERE phone = ?";
  public static final String SELECT_EMPLOYEE_BY_ROLE_MANAGER =
      "SELECT * FROM employee WHERE role = 'MANAGER'";
}
