package org.zeta.hr.management.presentation;

import static org.zeta.hr.management.presentation.ManagerPresentation.applyLeave;
import static org.zeta.hr.management.presentation.ManagerPresentation.cancelLeave;

import java.util.List;
import java.util.Scanner;

import org.zeta.hr.management.entity.Employee;
import org.zeta.hr.management.entity.Leave;
import org.zeta.hr.management.enums.EmployeeRole;
import org.zeta.hr.management.exception.ResourceNotFoundException;
import org.zeta.hr.management.service.EmployeeService;
import org.zeta.hr.management.service.LeaveService;
import org.zeta.hr.management.service.impl.EmployeeServiceImpl;
import org.zeta.hr.management.service.impl.LeaveServiceImpl;

public class EngineerPresentation {
  static EmployeeService employeeService = new EmployeeServiceImpl();
  static LeaveService leaveService = new LeaveServiceImpl();

  public static void engineerMenu(Scanner scanner, Employee employee) {
    System.out.println(
        "\033[1;37m ------------------------------- Engineer Menu"
            + " --------------------------------\033[0m");
    System.out.println("1. Apply for Leave");
    System.out.println("2. View my leaves");
    System.out.println("3. Update Profile");
    System.out.println("4. Cancel Leave");
    System.out.println("5. Logout");

    System.out.print("Enter your choice: ");

    int choice = scanner.nextInt();

    switch (choice) {
      case 1:
        applyLeave(scanner, employee);
        break;
      case 2:
        viewLeave(employee);
        break;
      case 3:
        updateProfile(scanner, employee);
        break;
      case 4:
        cancelLeave(scanner, employee);
        break;
      case 5:
        System.out.println("Logging out...");
        return;
      default:
        System.out.println("Invalid choice. Please try again.");
        break;
    }
    engineerMenu(scanner, employee);
  }

  private static void viewLeave(Employee employee) {
    System.out.println(
        "\033[1;37m ------------------------------- My Leaves"
            + " --------------------------------\033[0m");
    try {
      List<Leave> leave = leaveService.viewLeavesByEmployee(employee.getId());
      System.out.println(leave);
    } catch (ResourceNotFoundException e) {
      System.out.println("Error fetching leaves: " + e.getMessage());
    }
  }

  private static void updateProfile(Scanner scanner, Employee employee) {
    System.out.println(
        "\033[1;37m ------------------------------- Update Profile"
            + " --------------------------------\033[0m");
    System.out.print("Enter new First Name or press enter to retain the value: ");
    String firstName = scanner.nextLine();
    if (firstName.isEmpty()) {
      firstName = employee.getFirstName();
    }
    System.out.print("Enter new Last Name or press enter to retain the value: ");
    String lastName = scanner.nextLine();
    if (lastName.isEmpty()) {
      lastName = employee.getLastName();
    }
    System.out.print("Enter new Role (e.g., ENGINEER) or press enter to retain the value: ");
    String roleInput = scanner.nextLine();
    String role = employee.getRole().toString();
    if (!roleInput.isEmpty()) {
      try {
        role = roleInput.toUpperCase();
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid role. Retaining current role: " + employee.getRole());
      }
    }
    System.out.print("Enter new Reports To (Manager ID) or press enter to retain the value: ");
    String reportsToInput = scanner.nextLine();
    int reportsTo = employee.getReportsTo();
    if (!reportsToInput.isEmpty()) {
      try {
        reportsTo = Integer.parseInt(reportsToInput);
      } catch (NumberFormatException e) {
        System.out.println(
            "Invalid Reports To ID. Retaining current value: " + employee.getReportsTo());
      }
    }
    System.out.print("Enter new Email ID or press enter to retain the value: ");
    String emailId = scanner.nextLine();
    if (emailId.isEmpty()) {
      emailId = employee.getEmailId();
    }
    System.out.print("Enter new Phone Number or press enter to retain the value: ");
    String phone = scanner.nextLine();
    if (phone.isEmpty()) {
      phone = employee.getPhone();
    }
    System.out.print("Enter new City or press enter to retain the value: ");
    String city = scanner.nextLine();
    if (city.isEmpty()) {
      city = employee.getCity();
    }
    System.out.print("Enter new Locality or press enter to retain the value: ");
    String locality = scanner.nextLine();
    if (locality.isEmpty()) {
      locality = employee.getLocality();
    }
    System.out.print("Enter new State or press enter to retain the value: ");
    String state = scanner.nextLine();
    if (state.isEmpty()) {
      state = employee.getState();
    }
    System.out.print("Enter new Pin Code or press enter to retain the value: ");
    String pinCode = scanner.nextLine();
    if (pinCode.isEmpty()) {
      pinCode = employee.getPinCode();
    }

    employeeService.updateEmployee(
        employee.getId(),
        firstName,
        lastName,
        EmployeeRole.valueOf(role),
        reportsTo,
        emailId,
        phone,
        city,
        locality,
        state,
        pinCode);
    System.out.println("Profile updated successfully!");
  }
}
