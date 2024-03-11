package com.medkandirou.youwatch.comment;


import com.medkandirou.youwatch.helpers.Video_channel_Id;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
public class Comment {
    @EmbeddedId
    @NotNull private Video_channel_Id video_channel_id;
    @NotNull private String content;
    @NotNull private LocalDateTime datePosting;
}
