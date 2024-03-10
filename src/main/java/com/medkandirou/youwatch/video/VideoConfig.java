package com.medkandirou.youwatch.video;


import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.channel.Role;
import com.medkandirou.youwatch.security.auth.AuthenticationRequest;
import com.medkandirou.youwatch.security.auth.AuthenticationService;
import com.medkandirou.youwatch.security.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Order(3)
public class VideoConfig {

    @Bean
    CommandLineRunner commandLineRunnerVideo(VideoRepository videoRepository, AuthenticationService authenticationService) {
        return args -> {
            Category c1= new Category();
            c1.setId(1);
            Channel channel1= new Channel();
            channel1.setId(1L);
            Video video1 = new Video("title 1", "Description1", "Link1", "https://flowbite.com/docs/images/people/profile-picture-3.jpg", LocalDate.now(), 10000,c1,channel1);
            Video video2 = new Video("title 2", "Description2", "Link2", "https://flowbite.com/docs/images/people/profile-picture-3.jpg", LocalDate.now(), 5000,c1,channel1);
            Video video3 = new Video("title 3", "Description3", "Link3", "https://flowbite.com/docs/images/people/profile-picture-3.jpg", LocalDate.now(), 78000,c1,channel1);
            Video video4 = new Video("title 4", "Description4", "Link4", "https://flowbite.com/docs/images/people/profile-picture-3.jpg", LocalDate.now(), 100,c1,channel1);

            videoRepository.saveAll(List.of(video1,video2,video3,video4));
        };
    }
}
