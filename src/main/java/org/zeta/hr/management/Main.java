package org.zeta.hr.management;

import org.zeta.hr.management.enums.EmployeeRole;
import org.zeta.hr.management.service.EmployeeService;
import org.zeta.hr.management.service.impl.EmployeeServiceImpl;

public class Main {
  public static void main(String[] args) {
    EmployeeService employeeService = new EmployeeServiceImpl();
    employeeService.onboardEmployee(
        "Dhekshagna",
        "Goli",
        EmployeeRole.HR,
        0,
        "dhekshagna@gmail.com",
        "8097635734",
        "Hyderabad",
        "Kukatpally",
        "Telangana",
        "500072");
  }
}
