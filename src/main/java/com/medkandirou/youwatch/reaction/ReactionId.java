package com.medkandirou.youwatch.reaction;

import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.video.Video;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ReactionId implements Serializable {
    @ManyToOne
    private Channel channel;
    @ManyToOne
    private Video video;
}
