package com.medkandirou.youwatch.reaction;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReactionDTOreq {
    @NotNull(message = "video_channel_id is required")
    private Video_channel_Id video_channel_id;
    @NotNull(message = "reaction is required")
    private boolean reaction;
}
