package pl.sda.dao;

import pl.sda.dto.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDao {

    public EmployeeDao() {
    }

    private List<Employee> employees;

    public List<Employee> getAll(){
        // otwarcie połączenia z bazą
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa.hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // pobranie tabeli
        entityManager.getTransaction().begin();
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();
        entityManager.getTransaction().commit();

        // zamknięcie połączenia z bazą
        entityManager.close();
        entityManagerFactory.close();

        return employees;

    }

    //public Employee get(int employeeId){

    //};

    public void delete(int employeeId){

    };

    public void save(Employee employee){

    };

    public void update(Employee employee){

    };




}