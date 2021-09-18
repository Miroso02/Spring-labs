package ua.spring.lab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Second implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("second");
    }
}
