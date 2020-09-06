package pl.sda.dao;

import org.junit.Ignore;
import org.junit.jupiter.api.*;
import pl.sda.dao.EmployeeDao;
import pl.sda.database_connection.Connection;
import pl.sda.dto.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Ignore
class EmployeeDaoTest {

    public static EmployeeDao employeeDao;
    public static List<Employee> testList;

    @BeforeAll
    static void setUp() {
        Connection.startConnection();
        employeeDao = new EmployeeDao();
        testList = List.of(
                new Employee(1L, "Jan", "Kowalski", "Developer", 5000, 1985),
                new Employee(2L, "Tomasz", "Nowak", "Manager", 8000, 1970)
        );
    }


    @Test
    public void getAllTest() {
        List<Employee> listFromDatabase = employeeDao.getAll();
        assertNotEquals(0,listFromDatabase.size());
    }



    @Test
    public void getIfNotExistTest() {
        Employee employee = employeeDao.get(-3L);
        assertNull(employee.getFirstName());
    }

    @Test
    public void saveAndDeleteTest() {

        Employee employee = new Employee(999999999999L, "Zbigniew", "Mickiewicz", "Manager", 10000, 1999);
        employeeDao.save(employee);
        Employee employeeSaved = employeeDao.get(999999999999L);
        assertEquals(employeeSaved.getFirstName(), "Zbigniew");



        employeeDao.delete(999999999999L);
        Employee employeeDeleted = employeeDao.get(999999999999L);
        assertNull(employeeDeleted.getFirstName());


    }

    @Test
    public void updateTest(){
        Employee employeeUpdated = new Employee(1L, "Jan", "Kowalski", "Developer", 15000, 1985);
        employeeDao.update(1L,employeeUpdated);
        assertEquals(employeeUpdated.getSalary(), 15000);
    }



    @AfterAll
    static void tearDown() {
        Connection.closeConnection();
    }


}