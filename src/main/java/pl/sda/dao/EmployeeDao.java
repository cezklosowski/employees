package pl.sda.dao;

import pl.sda.database_connection.Connection;
import pl.sda.dto.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDao {

    public EmployeeDao() {
    }

    //private List<Employee> employees;

    public List<Employee> getAll(){

        // pobranie tabeli
        Connection.entityManager.getTransaction().begin();
        TypedQuery<Employee> query = Connection.entityManager.createQuery(
                "SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();
        Connection.entityManager.getTransaction().commit();

        return employees;

    }

    public Employee get(Long employeeId){
        Connection.entityManager.getTransaction().begin();
        TypedQuery<Employee> typedQuery = Connection.entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.id = :id", Employee.class);
        typedQuery.setParameter("id", employeeId);
        Employee employee = typedQuery.getSingleResult();
        return employee;

    }

    public void delete(int employeeId){

    };

    public void save(Employee employee){

    };

    public void update(Employee employee){

    };




}