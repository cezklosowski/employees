package pl.sda.dao;

import pl.sda.database_connection.Connection;
import pl.sda.dto.Employee;
import pl.sda.dto.Task;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class TaskDao {

    public TaskDao() {
    }

    public List<Task> tasks(long employeeId){

        TypedQuery<Task> typedQuery = Connection.entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.employeeId = :id", Task.class);
        typedQuery.setParameter("id", employeeId);

        List<Task> tasks = typedQuery.getResultList();

        return tasks;
    }

    public Task getTask(long taskId){
        Task task = new Task();
        try {
            TypedQuery<Task> typedQuery = Connection.entityManager.createQuery(
                    "SELECT t FROM Task t WHERE t.id = :id", Task.class);
            typedQuery.setParameter("id", taskId);
            task = typedQuery.getSingleResult();

        } catch (NoResultException e){
            // nie ma takiego zadania
        }
        return task;
    }

    public void addTask(long employeeId, Task task){
        Connection.entityManager.getTransaction().begin();

        EmployeeDao employeeDao = new EmployeeDao();

        if (!employeeDao.get(employeeId).equals(null)) {
            Employee employee = employeeDao.get(employeeId);
            List<Task> updatedTasks = employee.getTasks();

            updatedTasks.add(task);
            task.setEmployeeId(employeeId);
            Connection.entityManager.persist(task);
            employee.setTasks(updatedTasks);
        }
        Connection.entityManager.getTransaction().commit();

    }

    public void deleteTask(long taskId){
        try {
            Connection.entityManager.getTransaction().begin();
            Task task = getTask(taskId);
            Connection.entityManager.remove(task);
            Connection.entityManager.getTransaction().commit();
        } catch (NoResultException e){
            // Nie ma zadania o podanym id.
        }
    };

    public void updateTask(long taskId, Task task){

        Connection.entityManager.getTransaction().begin();

        TypedQuery<Task> typedQuery = Connection.entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.id = :id", Task.class);
        typedQuery.setParameter("id", taskId);

        Task taskUpdated = typedQuery.getSingleResult();

        if (!taskUpdated.equals(null)) {
            taskUpdated.setName(task.getName());
            taskUpdated.setDate(task.getDate());
            taskUpdated.setDone(task.isDone());
        }
        Connection.entityManager.getTransaction().commit();
    };
}
