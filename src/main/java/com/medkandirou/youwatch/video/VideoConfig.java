package com.medkandirou.youwatch.video;


import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.category.CategoryRepository;
import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.Role;
import com.medkandirou.youwatch.security.auth.AuthenticationService;
import com.medkandirou.youwatch.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Configuration
@Order(3)
public class VideoConfig {

    @Bean
    CommandLineRunner commandLineRunnerVideo(VideoRepository videoRepository, CategoryRepository categoryRepository,AuthenticationService authenticationService) {
        return args -> {
            Category c1 = new Category("Music");
            Category c2 = new Category("Gaming");
            Category c3 = new Category("Education");
            Category c4 = new Category("Cooking");
            Category c6 = new Category("Sports");
            Category c7 = new Category("Fashion");
            Category c8 = new Category("Travel");
            Category c10 = new Category("Comedy");
            categoryRepository.saveAll(List.of(c1,c2,c3,c4,c6,c7,c8,c10));




            RegisterRequest ch1 = new RegisterRequest("Med", "Kandirou", "user@gmail.com", "password", Role.USER);
            RegisterRequest ch2 = new RegisterRequest("Jane", "Smith", "admin@gmail.com", "password",Role.ADMIN);
            RegisterRequest ch3 = new RegisterRequest("Alice", "Johnson", "alice@gmail.com", "password",Role.USER);
            RegisterRequest ch4 = new RegisterRequest("Bob", "Brown", "bob@gmail.com", "password",Role.USER);
            authenticationService.register(ch1);
            authenticationService.register(ch2);
            authenticationService.register(ch3);
            authenticationService.register(ch4);




            Channel channel1= new Channel();
            channel1.setId(1L);
            Channel channel3= new Channel();
            channel3.setId(3L);
            Video video1 = new Video("Leo messi destoy Real Madrid 2017", "Match 2017 , barcelone vs real madrid in league spain ", "http://127.0.0.1:5000/uploads/fe8868bf-9ac7-4eea-8fe5-e9fc8cf8eaee.mp4", "http://127.0.0.1:5000/uploads/9057fa7b-071d-4c8d-8e35-5d19107a0868.jpg" ,LocalDateTime.now(),100000, 10000,c6,channel3);
            Video video2 = new Video("Youcode school promo 2022/2024 ", "Le numérique et les nouvelles technologies sont partout autour de nous, mais leurs codes et leurs langages ne sont pas toujours naturels et innés pour tous.C'est pourquoi YouCode veut mettre le code entre toutes les mains pour réduire les inégalités", "http://127.0.0.1:5000/uploads/7409da24-ad41-44e0-8303-ec2ede937194.mp4", "http://127.0.0.1:5000/uploads/4594dc1c-905d-4c94-80f4-9d5671aeaf50.jpg", LocalDateTime.now(), 50000,5000,c3,channel1);
            Video video3 = new Video("Fist match of brahim diaz with morrocco", "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus\n" +
                    "        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer\n" +
                    "        labore wes anderson cred nesciunt sapiente ea proident.", "http://127.0.0.1:5000/uploads/f65d79cb-4cd3-41ea-86d3-aeaaef9325e4.mp4", "http://127.0.0.1:5000/uploads/ee106420-d3c3-41f4-9157-97f854774ac5.jpg", LocalDateTime.now(), 99000,78000,c6,channel1);
            Video video4 = new Video("Coding in real life", "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus\n" +
                    "        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer\n" +
                    "        labore wes anderson cred nesciunt sapiente ea proident.", "http://127.0.0.1:5000/uploads/91616489-0b46-4edc-9c5f-92ebef90a5b3.mp4", "http://127.0.0.1:5000/uploads/e4f108a3-cab0-47ab-bab2-310cf7c3b404.jpg", LocalDateTime.now(), 93000,100,c10,channel3);
            Video video5 = new Video("Algerie vs cameron (but ekambi)", "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus\n" +
                    "        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer\n" +
                    "        labore wes anderson cred nesciunt sapiente ea proident.", "http://127.0.0.1:5000/uploads/82c01bbc-91de-491a-aee7-2529e15be6da.mp4", "http://127.0.0.1:5000/uploads/44397a5d-b51e-4a18-807d-c151b45d2ce6.jpg", LocalDateTime.now(), 93000,100,c10,channel3);

            Video video6 = new Video("Match with friends ( sun 95 vs java ventures )", "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus\n" +
                    "        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer\n" +
                    "        labore wes anderson cred nesciunt sapiente ea proident.", "http://127.0.0.1:5000/uploads/50e71e68-e66c-4770-b4f1-0602b18b2f94.mp4", "http://127.0.0.1:5000/uploads/7d2b3b3b-c938-42ab-a8cc-0d9dd0492c21.jpg", LocalDateTime.now(), 93000,100,c6,channel3);

            videoRepository.saveAll(List.of(video1,video2,video3,video4,video5,video6));
        };
    }
}
