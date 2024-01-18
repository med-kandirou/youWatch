package com.medkandirou.youwatch.vue;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VueDTOreq {
    @NotNull(message = "video_channel_id is required")
    private Video_channel_Id video_channel_id;
    @NotNull(message = "Creation date cannot be null")
    @PastOrPresent(message = "Creation date must be in the past or present")
    private LocalDate date;
}
