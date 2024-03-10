package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.channel.Channel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String title;
    @NonNull private String description;
    @NonNull private String link;
    @NonNull private String thumbnail;
    @NonNull private LocalDate datePosting;
    @NonNull private Integer nbrVues;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Channel channel;

    //favories
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favories",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private List<Channel> channels;
}
