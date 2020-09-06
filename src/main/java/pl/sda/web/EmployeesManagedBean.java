package pl.sda.web;


import pl.sda.dao.EmployeeDao;
import pl.sda.dao.TaskDao;
import pl.sda.dto.Employee;
import pl.sda.dto.Task;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SessionScoped
@ManagedBean(name = "employeeList")
public class EmployeesManagedBean {

    private List<Employee> employees = new ArrayList<>();
    private Employee newEmployee = new Employee();
    private Employee updateEmployee = new Employee();
    private EmployeeDao employeeDao = new EmployeeDao();


    private List<Task> tasks = new ArrayList<>();
    private Task newTask = new Task();
    private Task updateTask = new Task();
    private TaskDao taskDao = new TaskDao();

    @PostConstruct
    public void init() {

    }


    public List<Employee> getList() {
        employees = employeeDao.getAll();
        return employees;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void loadToUpdate(long id){


        updateEmployee = employeeDao.get(id);
        //updateEmployee = employees.stream().filter(employee -> employee.getId() == id).findFirst().get();

    }





    public void addNewEmployee() {
        //newEmployee.setId((new Random()).nextLong());
        employeeDao.save(newEmployee);
        employees.add(newEmployee);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Nowy pracownik został dodany!"));
    }


    public void deleteEmployee(long id) {

        employeeDao.delete(id);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Pracownik został usunięty!"));
    }

    public void updateEmployee() {

        employeeDao.update(updateEmployee.getId(),updateEmployee);


        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Pracownik został zaktualizowany!"));
    }



    public Employee getNewEmployee() {
        return newEmployee;
    }


    public Employee getUpdateEmployee() {
        return updateEmployee;
    }

    public void setUpdateEmployee(Employee updateEmployee) {
        this.updateEmployee = updateEmployee;
    }


    public List<Task> getTaskListByEmployee(long employeeId) {
        tasks = taskDao.getTasksByEmployee(employeeId);
        return tasks;
    }
/*

    public void loadToUpdateTask(long id){
        updateTask = employeeDao.getTask(id);
    }

    public void addNewTask(long employeeId) {
        newTask.setId((new Random()).nextLong());
        employeeDao.addTask(employeeId,newTask);
        tasks.add(newTask);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Nowe zadanie zostało dodane!"));
    }

    public void deleteTask(long taskId) {

        employeeDao.deleteTask(taskId);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Zadanie zostało usunięte!"));
    }

    public void updateTask() {

        employeeDao.updateTask(updateTask.getId(),updateTask);


        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Zadanie zostało zaktualizowane!"));
    }


 */


}
