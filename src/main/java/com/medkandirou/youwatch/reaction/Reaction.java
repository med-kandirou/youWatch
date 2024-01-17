package com.medkandirou.youwatch.reaction;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Entity
public class Reaction {
    @EmbeddedId
    @NotNull private Video_channel_Id video_channel_id;
    @NotNull private boolean reaction;
}
