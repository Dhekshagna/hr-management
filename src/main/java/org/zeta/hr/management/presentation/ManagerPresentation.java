package org.zeta.hr.management.presentation;

import java.util.List;
import java.util.Scanner;

import org.zeta.hr.management.entity.Employee;
import org.zeta.hr.management.entity.Leave;
import org.zeta.hr.management.enums.LeaveStatus;
import org.zeta.hr.management.enums.LeaveType;
import org.zeta.hr.management.exception.ResourceNotFoundException;
import org.zeta.hr.management.service.EmployeeService;
import org.zeta.hr.management.service.LeaveService;
import org.zeta.hr.management.service.impl.EmployeeServiceImpl;
import org.zeta.hr.management.service.impl.LeaveServiceImpl;

public class ManagerPresentation {
  static LeaveService leaveService = new LeaveServiceImpl();
  static EmployeeService employeeService = new EmployeeServiceImpl();

  public static void managerMenu(Scanner scanner, Employee employee) {
    System.out.println(
        "\033[1;37m ------------------------------- Manager Menu"
            + " --------------------------------\033[0m");
    System.out.println("1. Apply Leave");
    System.out.println("2. Approve/Reject Leave Requests");
    System.out.println("3. View Pending Leave Requests");
    System.out.println("4. View My Employees");
    System.out.println("5. View My Leaves");
    System.out.println("6. Cancel Leave");
    System.out.println("7. View Employee Details");
    System.out.println("8. Logout");

    int choice = scanner.nextInt();

    switch (choice) {
      case 1:
        applyLeave(scanner, employee);
        break;
      case 2:
        updateLeaveStatus(scanner, employee);
        break;
      case 3:
        viewPendingLeaves(employee);
        break;
      case 4:
        viewEmployessUnderManager(employee);
        break;
      case 5:
        viewLeavesByEmployee(employee);
        break;
      case 6:
        cancelLeave(scanner, employee);
        break;
      case 7:
        viewEmployeeDetails(scanner, employee);
        break;
      case 8:
        System.out.println("Logging out...");
        return;
      default:
        System.out.println("Invalid choice. Please try again.");
    }
    managerMenu(scanner, employee);
  }

  public static void applyLeave(Scanner scanner, Employee employee) {
    System.out.println(
        "Applying leave for " + employee.getFirstName() + " " + employee.getLastName());
    System.out.print("Enter Leave Type (SICK, PAID): ");
    String leaveType = scanner.next();
    System.out.print("Enter Start Date (YYYY-MM-DD): ");
    String startDate = scanner.next();
    System.out.print("Enter End Date (YYYY-MM-DD): ");
    String endDate = scanner.next();
    System.out.print("Enter Reason for Leave: ");
    String reason = scanner.next();

    leaveService.applyLeave(
        employee.getId(), LeaveType.valueOf(leaveType), startDate, endDate, reason);
  }

  public static void viewPendingLeaves(Employee employee) {
    System.out.println(
        "Viewing pending leaves for " + employee.getFirstName() + " " + employee.getLastName());
    List<Leave> pendingLeaves = leaveService.viewPendingLeavesUnderManager(employee.getId());
    try {
      for (Leave leave : pendingLeaves) {
        System.out.println(leave);
        System.out.println("-----------------------------");
      }
    } catch (ResourceNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void viewLeavesByEmployee(Employee employee) {
    System.out.println(
        "Viewing leaves for " + employee.getFirstName() + " " + employee.getLastName());
    List<Leave> leaves = leaveService.viewLeavesByEmployee(employee.getId());
    try {
      for (Leave leave : leaves) {
        System.out.println(leave);
        System.out.println("-----------------------------");
      }
    } catch (ResourceNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void cancelLeave(Scanner scanner, Employee employee) {
    System.out.print("Enter Leave ID to cancel: ");
    int leaveId = scanner.nextInt();
    try {
      leaveService.cancelLeave(leaveId, employee.getId());
    } catch (ResourceNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void viewEmployeeDetails(Scanner scanner, Employee employee) {
    System.out.println(
        "Viewing details for " + employee.getFirstName() + " " + employee.getLastName());
    System.out.print("Enter Employee ID to view details: ");
    int employeeId = scanner.nextInt();
    try {
      Leave leave = leaveService.viewLeaveDetails(employeeId, employee.getId());
      System.out.println(leave);
    } catch (ResourceNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void updateLeaveStatus(Scanner scanner, Employee employee) {
    System.out.print("Enter Leave ID to update: ");
    int leaveId = scanner.nextInt();
    System.out.print("Enter new status (APPROVED, REJECTED): ");
    String status = scanner.next();
    try {
      leaveService.updateLeaveStatus(leaveId, LeaveStatus.valueOf(status), employee.getId());
      System.out.println("Leave status updated successfully.");
    } catch (ResourceNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  public static void viewEmployessUnderManager(Employee employee) {
    System.out.println(
        "Viewing employees under " + employee.getFirstName() + " " + employee.getLastName());
    // Assuming there's a method in EmployeeService to get employees under a manager
    List<Employee> employees =
        employeeService.getEmployeesByManager(employee.getId()); // Placeholder method
    if (employees.isEmpty()) {
      System.out.println("No employees found under this manager.");
    } else {
      for (Employee emp : employees) {
        System.out.println(emp);
        System.out.println("-----------------------------");
      }
    }
  }
}
