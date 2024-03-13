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
            Category c5 = new Category("Technology");
            Category c6 = new Category("Sports");
            Category c7 = new Category("Fashion");
            Category c8 = new Category("Travel");
            Category c9 = new Category("Health & Fitness");
            Category c10 = new Category("Comedy");
            categoryRepository.saveAll(List.of(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10));




            RegisterRequest ch1 = new RegisterRequest("John", "Doe", "user@gmail.com", "password", Role.USER);
            RegisterRequest ch2 = new RegisterRequest("Jane", "Smith", "admin@gmail.com", "password",Role.ADMIN);
            RegisterRequest ch3 = new RegisterRequest("Alice", "Johnson", "alice@gmail.com", "password",Role.USER);
            RegisterRequest ch4 = new RegisterRequest("Bob", "Brown", "bob@gmail.com", "password",Role.USER);
            authenticationService.register(ch1);
            authenticationService.register(ch2);
            authenticationService.register(ch3);
            authenticationService.register(ch4);




            Channel channel1= new Channel();
            channel1.setId(1L);
            Video video1 = new Video("title 1", "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus\n" +
                    "        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer\n" +
                    "        labore wes anderson cred nesciunt sapiente ea proident.", "https://player.cloudinary.com/embed/?cloud_name=drlfmis63&public_id=samples/dance-2", "https://flowbite.com/docs/images/people/profile-picture-3.jpg" ,LocalDateTime.now(),100000, 10000,c1,channel1);
            Video video2 = new Video("title 2", "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus\n" +
                    "        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer\n" +
                    "        labore wes anderson cred nesciunt sapiente ea proident.", "https://player.cloudinary.com/embed/?cloud_name=drlfmis63&public_id=samples/dance-2", "https://flowbite.com/docs/images/people/profile-picture-3.jpg", LocalDateTime.now(), 50000,5000,c1,channel1);
            Video video3 = new Video("title 3", "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus\n" +
                    "        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer\n" +
                    "        labore wes anderson cred nesciunt sapiente ea proident.", "https://player.cloudinary.com/embed/?cloud_name=drlfmis63&public_id=samples/dance-2", "https://flowbite.com/docs/images/people/profile-picture-3.jpg", LocalDateTime.now(), 99000,78000,c1,channel1);
            Video video4 = new Video("title 4", "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus\n" +
                    "        terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer\n" +
                    "        labore wes anderson cred nesciunt sapiente ea proident.", "https://player.cloudinary.com/embed/?cloud_name=drlfmis63&public_id=samples/dance-2", "https://flowbite.com/docs/images/people/profile-picture-3.jpg", LocalDateTime.now(), 93000,100,c1,channel1);

            videoRepository.saveAll(List.of(video1,video2,video3,video4));
        };
    }
}
