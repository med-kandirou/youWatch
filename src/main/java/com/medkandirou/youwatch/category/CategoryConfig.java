package com.medkandirou.youwatch.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class CategoryConfig {
    @Bean
    CommandLineRunner commandLineRunnerCategory(CategoryRepository categoryRepository) {
        return args -> {
            Category c1 = new Category("Category 1");
            Category c2 = new Category("Category 2");
            Category c3 = new Category("Category 3");
            Category c4 = new Category("Category 4");
            Category c5 = new Category("Category 5");

            categoryRepository.saveAll(List.of(c1,c2,c3,c4,c5));
        };
    }
}