import org.junit.jupiter.api.*;
import pl.sda.dao.EmployeeDao;
import pl.sda.database_connection.Connection;
import pl.sda.dto.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    public void getIfExistTest() {
        Employee employee = employeeDao.get(2L);
        assertEquals(employee.getFirstName(), "Tomasz");
    }

    @Test
    public void getIfNotExistTest() {
        Employee employee = employeeDao.get(-3L);
        assertNull(employee.getId());
    }

    @Test
    public void saveAndDeleteTest() {

        Employee employee = new Employee(3L, "Zbigniew", "Mickiewicz", "Manager", 10000, 1999);
        employeeDao.save(employee);
        Employee employeeSaved = employeeDao.get(3L);
        assertEquals(employeeSaved.getFirstName(), "Zbigniew");

        employeeDao.delete(3L);
        Employee employeeDeleted = employeeDao.get(3L);
        assertNull(employeeDeleted.getId());

    }


    @AfterAll
    static void tearDown() {
        Connection.closeConnection();
    }
}