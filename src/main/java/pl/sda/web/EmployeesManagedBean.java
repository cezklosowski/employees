package pl.sda.web;


import pl.sda.dao.EmployeeDao;
import pl.sda.dto.Employee;

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

    @PostConstruct
    public void init() {

    }


    public List<Employee> getList() {
        employees = employeeDao.getAll();
        return employees;
    }

    public void loadToUpdate(long id){
        System.out.println();
        System.out.println();
        System.out.println("JESTEM W LOADZIE");

        System.out.println();
        System.out.println();

        updateEmployee = employeeDao.get(id);
        //updateEmployee = employees.stream().filter(employee -> employee.getId() == id).findFirst().get();

    }

    public void addNewEmployee() {
        newEmployee.setId((new Random()).nextLong());
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
}
