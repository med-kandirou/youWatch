package com.medkandirou.youwatch.vue;

import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.video.Video;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class VueId implements Serializable {
    @ManyToOne
    private Channel channel;
    @ManyToOne
    private Video video;
}
