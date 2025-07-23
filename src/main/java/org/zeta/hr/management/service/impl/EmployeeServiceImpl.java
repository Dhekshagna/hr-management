package org.zeta.hr.management.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.zeta.hr.management.dao.EmployeeDAO;
import org.zeta.hr.management.entity.Employee;
import org.zeta.hr.management.enums.EmployeeRole;
import org.zeta.hr.management.exception.ResourceNotFoundException;
import org.zeta.hr.management.service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
  EmployeeDAO employeeDAO = new EmployeeDAO();

  @Override
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
      String pinCode) {
    Employee employee =
        new Employee(
            firstName, lastName, role, reportsTo, emailId, phone, city, locality, state, pinCode);
    Employee manager = employeeDAO.getEmployeeById(reportsTo);
    if (manager != null
        && (manager.getRole().equals(EmployeeRole.MANAGER)
            || manager.getRole().equals(EmployeeRole.CEO))) {
      Employee.setCounter(employeeDAO.countRowsInserted());
      employee.setId(Employee.getCounter());
      employeeDAO.insertEmployee(employee);
      System.out.println("Employee onboarded successfully with ID: " + employee.getId());
    } else {
      throw new ResourceNotFoundException(
          "Manager with ID " + reportsTo + " not found. Please ensure to choose a valid manager.");
    }
  }

  @Override
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
      String pinCode) {
    Employee employee = employeeDAO.getEmployeeById(id);
    if (employee == null) {
      throw new ResourceNotFoundException("Employee not found with ID: " + id);
    }
  }

  @Override
  public void deleteEmployee(int id) {
    // Implementation for deleting an employee
  }

  @Override
  public void viewEmployee(int id) {
    Employee employee = employeeDAO.getEmployeeById(id);
  }

  @Override
  public void viewAllEmployees() {
    // Implementation for viewing all employees
  }

  @Override
  public List<Employee> getEmployeesByManager(int managerId) {
    // Implementation for getting employees by manager
    return null; // Placeholder return
  }

  @Override
  public void updateLeaveBalanceSick(int id, LocalDate startDate, LocalDate endDate) {
    // Implementation for updating sick leave balance
  }

  @Override
  public void updateLeaveBalancePaid(int id, LocalDate startDate, LocalDate endDate) {
    // Implementation for updating paid leave balance
  }
}
