package com.medkandirou.youwatch.reaction;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReactionRepository extends JpaRepository<Reaction, Video_channel_Id> {
}
