package com.medkandirou.youwatch.vue;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VueDTOres {
    private Video_channel_Id video_channel_id;
    private LocalDate date;
}
