package com.example.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepo) {
        return args -> {
            if (userRepo.findByUsername("admin").isEmpty()) {
                User u = new User();
                u.setUsername("admin");
                u.setPassword("admin"); // demo only
                u.setRole("ADMIN");
                userRepo.save(u);
                System.out.println("Created default admin/admin");
            }
        };
    }
}
