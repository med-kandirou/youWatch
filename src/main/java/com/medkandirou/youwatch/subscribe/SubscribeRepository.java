package com.medkandirou.youwatch.subscribe;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, SubscribeId> {


    @Query("SELECT s FROM Subscribe s WHERE s.subscribeId.channelFollow.id = :channelId")
    List<Subscribe> findSubscribeByChannelId(Long channelId);



}
