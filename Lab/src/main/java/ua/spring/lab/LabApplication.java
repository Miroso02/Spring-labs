package ua.spring.lab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LabApplication implements CommandLineRunner {
    public static void main(String[] args) {
        System.out.println("1");
        SpringApplication.run(LabApplication.class, args);
        System.out.println("2");
    }

    @Override
    public void run(String... args) {
        System.out.println("Hello world!");
    }
}
