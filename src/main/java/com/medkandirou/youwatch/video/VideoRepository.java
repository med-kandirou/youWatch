package com.medkandirou.youwatch.video;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VideoRepository extends JpaRepository<Video,Long> {
    Page<Video> findAll(Pageable pageable);

    List<Video> findVideoByChannelId(Long channel_id);
}
