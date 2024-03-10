package com.medkandirou.youwatch.video;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;


@Data
public class VideoDTOreq {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Name cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Link cannot be null")
    private String link;

    @NotNull(message = "Thumbnail cannot be null")
    private String thumbnail;

    @NotNull(message = "datePosting date cannot be null")
    @PastOrPresent(message = "datePosting date must be in the past or present")
    private LocalDate datePosting;

    @NotNull(message = "Category cannot be null")
    private Integer categoryId;

    @NotNull(message = "Channel cannot be null")
    private Long channelId;
}
