package com.medkandirou.youwatch.vue;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VueRepository extends JpaRepository<Vue, Video_channel_Id> {

    @Query("SELECT v FROM Vue v WHERE v.video_channel_id.channel.id=:channelId order by v.date desc limit 6")
    List<Vue> findVueByChannelId(Long channelId);
}
