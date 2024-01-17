package com.medkandirou.youwatch.subscribe;

import com.medkandirou.youwatch.channel.Channel;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SubscribeId implements Serializable {
    @ManyToOne
    private Channel channel_follow;
    @ManyToOne
    private Channel channel_followed;
}
