package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDTOreq {
    @NotNull(message = "Video_channel_Id is required")
    @NotNull private Video_channel_Id video_channel_id;
    @NotNull(message = "comment is required")
    @NotNull private String comment;
}
