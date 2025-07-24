package org.zeta.hr.management.service.impl;

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
      EmployeeRole role,
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
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setRole(role);
    employee.setReportsTo(reportsTo);
    employee.setEmailId(emailId);
    employee.setPhone(phone);
    employee.setCity(city);
    employee.setLocality(locality);
    employee.setState(state);
    employee.setPinCode(pinCode);
    employeeDAO.updateEmployee(employee);
    System.out.println("Employee updated successfully with ID: " + id);
  }

  @Override
  public void deleteEmployee(int id) {
    Employee employee = employeeDAO.getEmployeeById(id);
    if (employee == null) {
      throw new ResourceNotFoundException("Employee not found with ID: " + id);
    }
    employeeDAO.deleteEmployee(id);
  }

  @Override
  public Employee viewEmployee(int id) {
    Employee employee = employeeDAO.getEmployeeById(id);
    if (employee == null) {
      throw new ResourceNotFoundException("Employee not found with ID: " + id);
    }
    return employee;
  }

  @Override
  public List<Employee> viewAllEmployees() {
    List<Employee> employees = employeeDAO.getAllEmployees();
    if (employees.isEmpty()) {
      throw new ResourceNotFoundException("No employees found in the system.");
    }
    return employees;
  }

  @Override
  public List<Employee> getEmployeesByManager(int managerId) {
    List<Employee> employees = employeeDAO.getEmployeesByManagerId(managerId);
    if (employees.isEmpty()) {
      throw new ResourceNotFoundException("No employees found for manager with ID: " + managerId);
    }
    return employees;
  }

  @Override
  public void updateLeaveBalanceSick(int id, int days) {

    if (days < 0) {
      throw new IllegalArgumentException("End date cannot be before start date.");
    }

    Employee employee = employeeDAO.getEmployeeById(id);

    employeeDAO.updateEmployeeLeaveBalanceSick(
        id, employee.getSickLeaves() - days, employee.getLeaveBalance() - days);
  }

  @Override
  public void updateLeaveBalancePaid(int id, int days) {
    if (days < 0) {
      throw new IllegalArgumentException("End date cannot be before start date.");
    }

    Employee employee = employeeDAO.getEmployeeById(id);

    employeeDAO.updateEmployeeLeaveBalancePaid(
        id, employee.getPaidLeaves() - days, employee.getLeaveBalance() - days);
  }

  @Override
  public void updateEmployeeReportsTo(int id, int newManagerId) {
    Employee employee = employeeDAO.getEmployeeById(id);
    if (employee == null) {
      throw new ResourceNotFoundException("Employee not found with ID: " + id);
    }
    Employee newManager = employeeDAO.getEmployeeById(newManagerId);
    if (newManager == null) {
      throw new ResourceNotFoundException("New manager not found with ID: " + newManagerId);
    }
    if (!newManager.getRole().equals(EmployeeRole.MANAGER)
        && !newManager.getRole().equals(EmployeeRole.CEO)) {
      throw new ResourceNotFoundException("New manager must be a Manager or CEO.");
    }
    employee.setReportsTo(newManagerId);
    employeeDAO.updateEmployeeReportsTo(id, newManagerId);
  }

  @Override
  public List<Employee> getAllManagers() {
    List<Employee> managers = employeeDAO.getAllManagers();
    if (managers.isEmpty()) {
      throw new ResourceNotFoundException("No managers found in the system.");
    }
    return managers;
  }

  @Override
  public Employee getEmployeeByEmailId(String emailId) {
    Employee employee = employeeDAO.getEmployeeByEmail(emailId);
    if (employee == null) {
      throw new ResourceNotFoundException("Employee not found with email: " + emailId);
    }
    return employee;
  }

  @Override
  public Employee getEmployeeByPhone(String phone) {
    Employee employee = employeeDAO.getEmployeeByPhone(phone);
    if (employee == null) {
      throw new ResourceNotFoundException("Employee not found with phone: " + phone);
    }
    return employee;
  }
}
