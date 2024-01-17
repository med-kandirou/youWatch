package com.medkandirou.youwatch.helpers;

import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.video.Video;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class Video_channel_Id implements Serializable {
    @ManyToOne
    private Channel channel;
    @ManyToOne
    private Video video;
}
