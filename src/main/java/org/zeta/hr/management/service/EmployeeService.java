package org.zeta.hr.management.service;

import java.util.List;

import org.zeta.hr.management.entity.Employee;
import org.zeta.hr.management.enums.EmployeeRole;

public interface EmployeeService {
  public void onboardEmployee(
      String firstName,
      String lastName,
      EmployeeRole role,
      int reportsTo,
      String emailId,
      String phone,
      String city,
      String locality,
      String state,
      String pinCode);

  public void updateEmployee(
      int id,
      String firstName,
      String lastName,
      EmployeeRole role,
      int reportsTo,
      String emailId,
      String phone,
      String city,
      String locality,
      String state,
      String pinCode);

  public void deleteEmployee(int id);

  public Employee viewEmployee(int id);

  public List<Employee> viewAllEmployees();

  public List<Employee> getEmployeesByManager(int managerId);

  public void updateLeaveBalanceSick(int id, int days);

  public void updateLeaveBalancePaid(int id, int days);

  public void updateEmployeeReportsTo(int id, int newManagerId);

  public List<Employee> getAllManagers();

  public Employee getEmployeeByEmailId(String emailId);

  public Employee getEmployeeByPhone(String phone);
}
