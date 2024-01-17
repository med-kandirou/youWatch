package com.medkandirou.youwatch.video;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
public class VideoDTOreq {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Link cannot be null")
    private String link;

    @NotNull(message = "Thumbnail cannot be null")
    private String thumbnail;

    @NotNull(message = "Date posting cannot be null")
    private LocalDate datePosting;

    @NotNull(message = "Category cannot be null")
    private Long categoryId;

    @NotNull(message = "Channel cannot be null")
    private Long channelId;
}
