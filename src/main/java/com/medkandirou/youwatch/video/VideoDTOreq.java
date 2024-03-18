package com.medkandirou.youwatch.video;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;


@Data
public class VideoDTOreq {
    private Long id;
    @NotNull(message = "title cannot be null")
    private String title;

    @NotNull(message = "Description cannot be null")
    private String description;

    @NotNull(message = "Link cannot be null")
    private String link;

    @NotNull(message = "Thumbnail cannot be null")
    private String thumbnail;

    @NotNull(message = "Category cannot be null")
    private Integer categoryId;

    @NotNull(message = "Channel cannot be null")
    private Long channelId;
}
