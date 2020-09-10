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

        System.out.println("Wywołany emp:" + updateEmployee.getId());

    }





    public void addNewEmployee() {

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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getNewTask() {
        return newTask;
    }

    public void setNewTask(Task newTask) {
        this.newTask = newTask;
    }

    public Task getUpdateTask() {
        return updateTask;
    }

    public void setUpdateTask(Task updateTask) {
        this.updateTask = updateTask;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<Task> getTasks(long employeeId) {
        updateEmployee = employeeDao.get(employeeId);
        tasks = taskDao.tasks(employeeId);

        newTask = new Task();

        return tasks;
    }

    public List<Task> userTasksReload(long employeeID){
        return tasks;
    }


    public void loadToTaskUpdate(long id){
        updateTask = taskDao.getTask(id);
    }

    public void addNewTask() {


        taskDao.addTask(updateEmployee.getId(),newTask);
        System.out.println("ID: " + updateEmployee.getId());
        tasks.add(newTask);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Nowe zadanie zostało dodane!"));
    }

    public void deleteTask(long taskId) {

        taskDao.deleteTask(taskId);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Zadanie zostało usunięte!"));
    }

    public void updateTask() {

        taskDao.updateTask(updateTask.getId(),updateTask);


        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Zadanie zostało zaktualizowane!"));
    }





}
