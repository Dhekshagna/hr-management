package org.zeta.hr.management;

import java.util.Scanner;

import org.zeta.hr.management.entity.Employee;
import org.zeta.hr.management.enums.EmployeeRole;
import org.zeta.hr.management.exception.ResourceNotFoundException;
import org.zeta.hr.management.presentation.EngineerPresentation;
import org.zeta.hr.management.presentation.HRPresentation;
import org.zeta.hr.management.presentation.ManagerPresentation;
import org.zeta.hr.management.service.EmployeeService;
import org.zeta.hr.management.service.impl.EmployeeServiceImpl;

public class Main {
  static EmployeeService employeeService = new EmployeeServiceImpl();

  public static void mainMenu(Scanner scanner) {
    System.out.println("\033[1;37m 1. Login using email\033[0m");
    System.out.println("\033[1;37m 2. Login using phone number\033[0m");
    System.out.println("\033[1;37m 3. Exit\033[0m");

    System.out.print("\033[1;34m Please select an option (1-3): \033[0m");

    int choice = scanner.nextInt();

    switch (choice) {
      case 1:
        loginUsingEmail(scanner);
        break;
      case 2:
        loginUsingPhoneNumber(scanner);
        break;
      case 3:
        System.out.println("\033[1;31m Exiting the application. Thank you!\033[0m");
        System.exit(0);
        break;
      default:
        System.out.println("\033[1;31m Invalid choice. Please try again.\033[0m");
        break;
    }
    mainMenu(scanner);
  }

  public static void main(String[] args) {

    System.out.println(
        "\033[1;37m"
            + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\033[0m");
    System.out.println(
        "\033[1;37m                                             ----------- Welcome to HR"
            + " Management System -----------        \033[1m");
    System.out.println(
        "\033[1;37m"
            + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\033[0m");
    mainMenu(new Scanner(System.in));
  }

  public static void loginUsingEmail(Scanner scanner) {
    System.out.println(
        "\033[1;37m ------------------------------- Login --------------------------------\033[0m");
    System.out.print("\033[1;37m Enter Email: \033[0m");
    String email = scanner.next();

    Employee employee;
    try {
      employee = employeeService.getEmployeeByEmailId (email);
    } catch (ResourceNotFoundException e) {
      System.out.println("\033[1;31m " + e.getMessage() + "\033[0m");
      return;
    }
    if (employee == null) {
      System.out.println("\033[1;31m Employee not found with email: " + email + "\033[0m");
    } else {
      System.out.println(employee);
      System.out.println(
          "\033[1;37m"
              + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\033[0m");
      if (employee.getRole() == EmployeeRole.MANAGER) {
        ManagerPresentation.managerMenu(scanner, employee);
      } else if (employee.getRole() == EmployeeRole.ENGINEER) {
        EngineerPresentation.engineerMenu(scanner, employee);
      } else {
        HRPresentation.hrMenu(scanner, employee);
      }
    }
  }

  public static void loginUsingPhoneNumber(Scanner scanner) {
    System.out.println(
        "\033[1;37m ------------------------------- Login --------------------------------\033[0m");
    System.out.print("\033[1;37m Enter Phone Number: \033[0m");
    String phoneNumber = scanner.next();
    Employee employee;
    try {
      employee = employeeService.getEmployeeByPhone(phoneNumber);
    } catch (ResourceNotFoundException e) {
      System.out.println("\033[1;31m " + e.getMessage() + "\033[0m");
      return;
    }
    if (employee == null) {
      System.out.println(
          "\033[1;31m Employee not found with phone number: " + phoneNumber + "\033[0m");
    } else {
      System.out.println(employee);
      System.out.println(
          "\033[1;37m"
              + " @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\033[0m");
      if (employee.getRole() == EmployeeRole.MANAGER) {
        ManagerPresentation.managerMenu(scanner, employee);
      } else if (employee.getRole() == EmployeeRole.ENGINEER) {
        EngineerPresentation.engineerMenu(scanner, employee);
      } else {
        HRPresentation.hrMenu(scanner, employee);
      }
    }
  }
}
