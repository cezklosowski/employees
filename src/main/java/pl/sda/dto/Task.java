package pl.sda.dto;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private long id;

    @Column(name="task_name")
    @Size(max=50)
    private String name;

    @Column(name="task_date")
    private LocalDate date;

    @Column(name="if_done")
    private boolean done;

    @Column(name="employee_id")
    private long employeeId;

    public Task() {
    }

    public Task(String name, LocalDate date, boolean done) {
        this.name = name;
        this.date = date;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }


}
