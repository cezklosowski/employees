package pl.sda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.database_connection.Connection;

@SpringBootApplication
public class SpringPrimeFacesApplication {

    public static void main(String[] args) {
        Connection.startConnection();
        SpringApplication.run(SpringPrimeFacesApplication.class, args);
        //Connection.closeConnection();

    }
}