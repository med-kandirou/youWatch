package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.categorie.Categorie;
import com.medkandirou.youwatch.channel.Channel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;
    @NotNull private String name;
    @NotNull private String description;
    @NotNull private String link;
    @NotNull private String thumbnail;
    @NotNull private LocalDate datePosting;

    @ManyToOne
    private Categorie categorie;

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
