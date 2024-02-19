package io.github.dolphin.software.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The UsersServiceApplication class is the main class for running the Users Service application.
 * This class is annotated with @SpringBootApplication, which is a convenience annotation that adds all the following:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services in the io.github.dolphin.software.users package, letting it find the controllers.
 */
@SpringBootApplication
public class UsersServiceApplication {

    /**
     * The main method is the entry point of the Users Service application. It starts the Spring Boot application by running
     * the UsersServiceApplication class using SpringApplication.run() method.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApplication.class, args);
    }

}
