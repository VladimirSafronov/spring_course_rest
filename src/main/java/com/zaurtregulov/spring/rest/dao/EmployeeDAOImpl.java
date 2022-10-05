package com.zaurtregulov.spring.rest.dao;

import com.zaurtregulov.spring.rest.entity.Employee;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

  @Autowired
  private SessionFactory sessionFactory;

  public List<Employee> getAllEmployees() {
    Session session = sessionFactory.getCurrentSession();
    Query<Employee> query = session.createQuery("from Employee", Employee.class);
    return query.getResultList();
  }

  public void saveEmployee(Employee employee) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(employee);
  }

  public Employee getEmployee(int id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Employee.class, id);
  }

  public void deleteEmployee(int id) {
    Session session = sessionFactory.getCurrentSession();
    Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId");
    query.setParameter("employeeId", id);
    query.executeUpdate();
  }
}
