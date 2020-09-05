import org.junit.jupiter.api.Test;
import pl.sda.dao.EmployeeDao;
import pl.sda.dto.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    @Test
    public void getAll(){
        List<Employee> testList = List.of(
                new Employee(1L,"Jan", "Kowalski", "Developer", 5000, 1985),
                new Employee(2L,"Tomasz", "Nowak", "Manager", 8000, 1970)
        );
        EmployeeDao employeeDao = new EmployeeDao();
        List<Employee> listFromDatabase = employeeDao.getAll();
        System.out.println();
        assertEquals(testList,listFromDatabase);
    }


}