package com.github.FridrikThor.task_tracker.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskConfig {

    /*@Bean
    CommandLineRunner commandLineRunner(
            TaskRepository repository){
        return args -> {
            Task titleFix = new Task(
                    "Title fix",
                    "Title is wrong",
                    "Pending",
                    "Low",
                    LocalDate.of(2024, Month.NOVEMBER, 5),
                    LocalDate.of(2024, Month.OCTOBER, 30)
            );
            Task createUserClass = new Task(
                    "create User class",
                    "We are adding the User class",
                    "Pending",
                    "High",
                    LocalDate.of(2024, Month.NOVEMBER, 14),
                    LocalDate.of(2024, Month.NOVEMBER, 7)
            );

            repository.saveAll(
                    List.of(titleFix, createUserClass)
            );

        };
    }*/
}
