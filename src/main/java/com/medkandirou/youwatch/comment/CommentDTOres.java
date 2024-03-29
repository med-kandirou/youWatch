package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDTOres {
    @NotNull private Video_channel_Id video_channel_id;
    @NotNull private String content;
    @NotNull private String datePosting;
}
