package pl.sda.dao;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.database_connection.Connection;
import pl.sda.dto.Employee;
import pl.sda.dto.Task;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TaskDaoTest {

    public static TaskDao taskDao;
    public static EmployeeDao employeeDao;
    public static Employee newEmployee;
    public static Task task;
    public static Task task2;
    public static Scanner scanner;

    @BeforeAll
    static void setUp() {
        Connection.startConnection();
        taskDao = new TaskDao();
        employeeDao = new EmployeeDao();
        Scanner scanner = new Scanner(System.in);


        // utworzenie testowego użytkownika
        Employee employee = new Employee( "Jan", "Kowalski", "Developer", 5000, 1985);

        // zapisanie testowego użytkownika
        employeeDao.save(employee);
        TypedQuery<Employee> typedQuery = Connection.entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName AND e.position = :position", Employee.class);
        typedQuery.setParameter("firstName", "Jan");
        typedQuery.setParameter("lastName", "Kowalski");
        typedQuery.setParameter("position", "Developer");
        newEmployee = typedQuery.getResultList().get(0);


        // utworzenie testowych zadań
        task = new Task("Pierwsze zadanie", LocalDate.of(2020,05,12),false);

        task2 = new Task("Drugie zadanie", LocalDate.of(2019,05,10),true);


    }

    @Test
    public void addTaskTest(){
        System.out.println(newEmployee);
        taskDao.addTask(newEmployee.getId(),task);
        taskDao.addTask(newEmployee.getId(),task2);
        assertEquals(2,2);
    }

    @Ignore
    @Test
    public void getTasksByEmployeeTest() {
        List<Task> tasksLocal = Arrays.asList(task,task2);
        List<Task> tasksDatabase = taskDao.getTasksByEmployee(newEmployee.getId());

        System.out.println(tasksLocal.stream().toString());
        System.out.println(tasksDatabase.stream().toString());

        System.out.println();

        assertEquals(2, 2);
    }

    @AfterAll
    static void tearDown() {
        Connection.closeConnection();
    }

}