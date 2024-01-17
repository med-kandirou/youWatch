package com.medkandirou.youwatch.vue;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;


@Data
@RequiredArgsConstructor
@Entity
public class Vue {
    @EmbeddedId
    @NotNull
    private Video_channel_Id video_channel_id;
    private LocalDate date;
}
