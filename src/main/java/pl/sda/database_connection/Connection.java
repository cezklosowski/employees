package pl.sda.database_connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Connection {
    public static EntityManagerFactory entityManagerFactory;
    public static EntityManager entityManager;

    public static void startConnection(){
        // otwarcie połączenia z bazą
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa.hibernate");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static void closeConnection(){
        // zamknięcie połączenia z bazą
        entityManager.close();
        entityManagerFactory.close();
    }
}
