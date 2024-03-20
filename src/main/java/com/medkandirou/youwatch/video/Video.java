package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.category.Category;
import com.medkandirou.youwatch.channel.Channel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
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
    @NonNull private LocalDateTime datePosting;
    @NonNull private Integer nbrVues;
    @NonNull private Integer nbrLikes;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NonNull private Category category;
    @ManyToOne
    @NonNull private Channel channel;

    //favories
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favories",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private List<Channel> channels;
}
