package com.medkandirou.youwatch.channel;


import com.medkandirou.youwatch.video.Video;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@RequiredArgsConstructor
@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull private Long id;
    @NotNull private String firstname;
    @NotNull private String lastName;
    @NotNull private LocalDate creationDate;
    @NotNull private String profil_img;
    @NotNull private String cover_img;
    @NotNull private String email;
    @NotNull private String password;

    //favories
    @ManyToMany(mappedBy = "channels")
    private Set<Video> videos = new HashSet<>();
}
