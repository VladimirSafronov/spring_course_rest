package com.zaurtregulov.spring.rest.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {

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
