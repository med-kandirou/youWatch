package com.medkandirou.youwatch.reaction;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import lombok.Data;


@Data
public class ReactionDTOres {
    private Video_channel_Id video_channel_id;
    private boolean reaction;
}
