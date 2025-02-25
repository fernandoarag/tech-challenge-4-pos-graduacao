package br.com.fiap.postech.customers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Customers Management Spring Boot application.
 */
@SpringBootApplication
public class CustomersApplication {

    /**
     * Main method to run the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CustomersApplication.class, args);
    }

}