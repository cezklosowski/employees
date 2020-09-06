package pl.sda.dao;

import pl.sda.database_connection.Connection;
import pl.sda.dto.Employee;
import pl.sda.dto.Task;

import javax.persistence.*;
import java.util.List;

public class EmployeeDao {

    public EmployeeDao() {
    }


    public List<Employee> getAll(){

        // pobranie tabeli

        TypedQuery<Employee> query = Connection.entityManager.createQuery(
                "SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();


        return employees;

    }

    public Employee get(long employeeId){
        Employee employee = new Employee();
        try {
            TypedQuery<Employee> typedQuery = Connection.entityManager.createQuery(
                    "SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
            typedQuery.setParameter("id", employeeId);
            employee = typedQuery.getSingleResult();


        } catch (NoResultException e){
            // nie ma takiego pracownika
        }
        return employee;
    }

    public void delete(long employeeId){
        try {
            Connection.entityManager.getTransaction().begin();
            Employee employee = get(employeeId);
            Connection.entityManager.remove(employee);
            Connection.entityManager.getTransaction().commit();
        } catch (NoResultException e){
            // Nie ma pracownika o podanym id.
        }
    };

    public void save(Employee employee){

        Employee newEmployee = new Employee();
        newEmployee.setId(employee.getId());
        newEmployee.setFirstName(employee.getFirstName());
        newEmployee.setLastName(employee.getLastName());
        newEmployee.setPosition(employee.getPosition());
        newEmployee.setSalary(employee.getSalary());
        newEmployee.setBirthYear(employee.getBirthYear());

        Connection.entityManager.getTransaction().begin();
        Connection.entityManager.persist(newEmployee);
        Connection.entityManager.getTransaction().commit();

    };

    public void update(long employeeId, Employee employee){

        Connection.entityManager.getTransaction().begin();

        Employee employeeUpdated = get(employeeId);
        if (!employeeUpdated.equals(null)) {
            employeeUpdated.setFirstName(employee.getFirstName());
            employeeUpdated.setLastName(employee.getLastName());
            employeeUpdated.setPosition(employee.getPosition());
            employeeUpdated.setSalary(employee.getSalary());
            employeeUpdated.setBirthYear(employee.getBirthYear());
        }

        Connection.entityManager.getTransaction().commit();

    };






}