package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.category.CategoryDTOres;
import com.medkandirou.youwatch.channel.ChannelDTOres;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class VideoDTOres {
    private Long id;
    private String title;
    private String description;
    private String link;
    private String thumbnail;
    private LocalDateTime datePosting;
    private Integer nbrVues;
    private CategoryDTOres category;
    private ChannelDTOres channel;
}
