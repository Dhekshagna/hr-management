package org.zeta.hr.management.service;

import java.time.LocalDate;
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
      String role,
      int reportsTo,
      String emailId,
      String phone,
      String city,
      String locality,
      String state,
      String pinCode);

  public void deleteEmployee(int id);

  public void viewEmployee(int id);

  public void viewAllEmployees();

  public List<Employee> getEmployeesByManager(int managerId);

  public void updateLeaveBalanceSick(int id, LocalDate startDate, LocalDate endDate);

  public void updateLeaveBalancePaid(int id, LocalDate startDate, LocalDate endDate);
}
