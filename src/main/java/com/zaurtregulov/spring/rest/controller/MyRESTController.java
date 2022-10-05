package com.zaurtregulov.spring.rest.controller;

import com.zaurtregulov.spring.rest.entity.Employee;
import com.zaurtregulov.spring.rest.exception_handling.EmployeeIncorrectData;
import com.zaurtregulov.spring.rest.exception_handling.NoSuchEmployeeException;
import com.zaurtregulov.spring.rest.service.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    Employee employee = employeeService.getEmployee(id);

    if(employee == null) {
      throw new NoSuchEmployeeException("There is no employee with id = " + id + " in Database.");
    }

    return employee;
  }

  //данной аанотацией помечаются метод,ответственный за обработку исключений
  @ExceptionHandler
  public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception) {
    EmployeeIncorrectData data = new EmployeeIncorrectData();
    //вносим информацию-сообщение,полученную из исключения
    data.setInfo(exception.getMessage());
    //возвращаем информацию.Первый параметр - объект,ответственный за JSON,второй - статус HTTP response
    return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception) {
    EmployeeIncorrectData data = new EmployeeIncorrectData();
    //вносим информацию-сообщение,полученную из исключения
    data.setInfo(exception.getMessage());
    //возвращаем информацию.Первый параметр - объект,ответственный за JSON,второй - статус HTTP response
    return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
  }
}
