package org.zeta.hr.management.presentation;

import static org.zeta.hr.management.presentation.ManagerPresentation.applyLeave;
import static org.zeta.hr.management.presentation.ManagerPresentation.cancelLeave;

import java.util.Scanner;

import org.zeta.hr.management.entity.Employee;
import org.zeta.hr.management.enums.EmployeeRole;
import org.zeta.hr.management.service.EmployeeService;
import org.zeta.hr.management.service.impl.EmployeeServiceImpl;

public class HRPresentation {
  static EmployeeService employeeService = new EmployeeServiceImpl();

  public static void hrMenu(Scanner scanner, Employee employee) {
    System.out.println(
        "\033[1;37m ------------------------------- HR Menu"
            + " --------------------------------\033[0m");
    System.out.println("1. Onboard Employee");
    System.out.println("2. Change Manager of Employee");
    System.out.println("3. Apply Leave");
    System.out.println("4. Cancel Leave");
    System.out.println("5. View All Managers");
    System.out.println("6. Exit");

    System.out.println("Enter your choice: ");

    int choice = scanner.nextInt();

    switch (choice) {
      case 1:
        onboardEmployee(scanner);
        break;
      case 2:
        changeManagerOfEmployee(scanner, employee);
        break;
      case 3:
        applyLeave(scanner, employee);
        break;
      case 4:
        cancelLeave(scanner, employee);
        break;
      case 5:
        viewAllManagers();
        break;
      case 6:
        System.out.println("Exiting HR Management System. Thank you!");
        return;
      default:
        System.out.println("Invalid choice. Please try again.");
        break;
    }
    hrMenu(scanner, employee);
  }

  private static void onboardEmployee(Scanner scanner) {
    System.out.println("------------------------Onboarding Employee------------------------");
    System.out.print("Enter First Name: ");
    String firstName = scanner.next();
    System.out.print("Enter Last Name: ");
    String lastName = scanner.next();
    System.out.print("Enter Role (e.g., MANAGER, CEO): ");
    String role = scanner.next().toUpperCase();
    System.out.print("Enter Reports To (Manager ID): ");
    int reportsTo = scanner.nextInt();
    System.out.print("Enter Email ID: ");
    String emailId = scanner.next();
    System.out.print("Enter Phone Number: ");
    String phone = scanner.next();
    System.out.print("Enter City: ");
    String city = scanner.next();
    System.out.print("Enter Locality: ");
    String locality = scanner.next();
    System.out.print("Enter State: ");
    String state = scanner.next();
    System.out.print("Enter Pin Code: ");
    String pinCode = scanner.next();
    try {
      employeeService.onboardEmployee(
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
    } catch (Exception e) {
      System.out.println("Error onboarding employee: " + e.getMessage());
    }
  }

  private static void changeManagerOfEmployee(Scanner scanner, Employee employee) {
    System.out.println(
        "------------------------Change Manager of Employee------------------------");
    viewAllManagers();
    System.out.print("Enter New Manager ID: ");
    int newManagerId = scanner.nextInt();
    try {
      employeeService.updateEmployeeReportsTo(employee.getId(), newManagerId);
      System.out.println("Manager changed successfully.");
    } catch (Exception e) {
      System.out.println("Error changing manager: " + e.getMessage());
    }
  }

  private static void viewAllManagers() {
    System.out.println("------------------------View All Employees------------------------");
    try {
      employeeService.getAllManagers().forEach(System.out::println);
    } catch (Exception e) {
      System.out.println("Error fetching employees: " + e.getMessage());
    }
  }
}
