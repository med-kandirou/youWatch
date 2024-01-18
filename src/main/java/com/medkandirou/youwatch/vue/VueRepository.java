package com.medkandirou.youwatch.vue;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VueRepository extends JpaRepository<Vue, Video_channel_Id> {
}
