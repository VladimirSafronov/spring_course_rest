package com.zaurtregulov.spring.rest.exception_handling;

//данный класс будет выводить текст с информацией об ошибке на экран

public class EmployeeIncorrectData {

  private String info;

  public EmployeeIncorrectData() {
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }
}
