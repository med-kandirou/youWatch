package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.video.Video;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@NoArgsConstructor
public class CommentId implements Serializable {
    @ManyToOne
    private Channel channel;
    @ManyToOne
    private Video video;
}
