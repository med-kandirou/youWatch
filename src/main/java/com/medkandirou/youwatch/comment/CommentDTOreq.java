package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentDTOreq {
    @NotNull(message = "videoId is required")
    private Long videoId;
    @NotNull(message = "channelId is required")
    private Long channelId;
    @NotNull(message = "content is required")
    private String content;
}
