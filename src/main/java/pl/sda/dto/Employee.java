package pl.sda.dto;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="first_name")
    @Size(max=15)
    private String firstName;
    @Column(name="last_name")
    @Size(max=15)
    private String lastName;
    @Column(name="position")
    @Size(max=15)
    private String position;
    @Column(name="salary")
    private Integer salary;
    @Column(name="birth_year")
    private Integer birthYear;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private List<Task> tasks;

    public Employee() {
        tasks = new ArrayList<>();
    }

    public Employee( String firstName, String lastName, String position, Integer salary, Integer birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.birthYear = birthYear;
    }

    public Employee(long id, String firstName, String lastName, String position, Integer salary, Integer birthYear) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.salary = salary;
        this.birthYear = birthYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(position, employee.position) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(birthYear, employee.birthYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, position, salary, birthYear);
    }
}
