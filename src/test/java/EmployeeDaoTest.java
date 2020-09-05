import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.dao.EmployeeDao;
import pl.sda.database_connection.Connection;
import pl.sda.dto.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    @BeforeEach
    void setUp() {
        Connection.startConnection();
    }

    @Test
    public void getAll(){
        List<Employee> testList = List.of(
                new Employee(1L,"Jan", "Kowalski", "Developer", 5000, 1985),
                new Employee(2L,"Tomasz", "Nowak", "Manager", 8000, 1970)
        );
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> listFromDatabase = employeeDao.getAll();
        assertEquals(testList,listFromDatabase);
    }

    @AfterEach
    void tearDown() {
        Connection.closeConnection();
    }
}