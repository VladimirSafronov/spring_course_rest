package com.zaurtregulov.spring.rest.controller;

import com.zaurtregulov.spring.rest.entity.Employee;
import com.zaurtregulov.spring.rest.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyRESTController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/employees")
  public List<Employee> showAllEmployees() {
    return employeeService.getAllEmployees();
  }

  //Аннотация @PathVariable используется для получения значения переменной из адреса запроса
  @GetMapping("/employees/{id}")
  public Employee getEmployee (@PathVariable int id) {
    //возвращается документ JSON,в кот.импортируется объект Employee благодаря
    //Spring(jackson databind)
    return employeeService.getEmployee(id);
  }
}
