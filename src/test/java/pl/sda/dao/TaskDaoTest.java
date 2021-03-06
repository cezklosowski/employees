package pl.sda.dao;

import org.junit.Ignore;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.database_connection.Connection;
import pl.sda.dto.Employee;
import pl.sda.dto.Task;

import javax.persistence.TypedQuery;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@Ignore
class TaskDaoTest {

    public static TaskDao taskDao;
    public static EmployeeDao employeeDao;
    public static Employee newEmployee;
    public static Employee newEmployee2;
    public static Task task;
    public static Task task2;
    public static Task task8;


    @BeforeAll
    static void setUp() {
        Connection.startConnection();
        taskDao = new TaskDao();
        employeeDao = new EmployeeDao();
        Scanner scanner = new Scanner(System.in);


        // utworzenie testowego użytkownika
        Employee employee = new Employee( "Jan", "Kowalski", "Developer", 5000, 1985);
        Employee employee2 = new Employee( "Zygmunt", "Nowak", "Manager", 8000, 1990);

        // zapisanie testowego użytkownika
        employeeDao.save(employee);
        TypedQuery<Employee> typedQuery = Connection.entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName AND e.position = :position", Employee.class);
        typedQuery.setParameter("firstName", "Jan");
        typedQuery.setParameter("lastName", "Kowalski");
        typedQuery.setParameter("position", "Developer");
        newEmployee = typedQuery.getResultList().get(0);

        employeeDao.save(employee2);
        TypedQuery<Employee> typedQuery2 = Connection.entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.firstName = :firstName AND e.lastName = :lastName AND e.position = :position", Employee.class);
        typedQuery.setParameter("firstName", "Zygmunt");
        typedQuery.setParameter("lastName", "Nowak");
        typedQuery.setParameter("position", "Manager");
        newEmployee2 = typedQuery.getResultList().get(0);


        // utworzenie testowych zadań
        task = new Task("Pierwsze zadanie", Date.valueOf(LocalDate.of(2020,05,12)),false);

        task2 = new Task("Drugie zadanie", Date.valueOf(LocalDate.of(2019,05,10)),true);
        task8 = new Task("Trzecie zadanie", Date.valueOf(LocalDate.of(2008,3,30)),true);




    }

    @Test
    public void addTaskTest(){

        taskDao.addTask(newEmployee.getId(),task);
        taskDao.addTask(newEmployee.getId(),task2);
        taskDao.addTask(newEmployee2.getId(),task8);

    }


    @Test
    public void getTasks() {
        addTaskTest();
        List<Task> tasksLocal = Arrays.asList(task,task2);
        List<Task> tasksDatabase = taskDao.tasks(newEmployee.getId());



        assertNotEquals(0, tasksDatabase.size());
    }

    @Test
    public void updateTaskTest(){
        Task task3 = new Task("nowe zadanie testowe",Date.valueOf(LocalDate.of(2020,11,11)),false);
        taskDao.addTask(newEmployee.getId(),task3);
        Connection.entityManager.persist(task3);

        Task task4 = new Task("nowe zadanie testowe zaktualizowane",Date.valueOf(LocalDate.of(2020,11,11)),true);

        taskDao.updateTask(task3.getId(),task4);



    }

    @Test
    public void deleteTaskTest(){
        Task task5 = new Task("naprawdę chwilowe zadanie testowe",Date.valueOf(LocalDate.of(2028,11,11)),false);
        taskDao.addTask(newEmployee.getId(),task5);
        Connection.entityManager.persist(task5);

        taskDao.deleteTask(task5.getId());

    }

    @AfterAll
    static void tearDown() {
        Connection.closeConnection();
    }

}