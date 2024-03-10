package com.medkandirou.youwatch.video;

import com.medkandirou.youwatch.category.CategoryDTOres;
import com.medkandirou.youwatch.channel.ChannelDTOres;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VideoDTOres {
    private Long id;
    private String title;
    private String description;
    private String link;
    private String thumbnail;
    private LocalDate datePosting;
    private CategoryDTOres category;
    private ChannelDTOres channel;
}
