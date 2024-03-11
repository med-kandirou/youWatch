package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Video_channel_Id> {

    @Query("SELECT c FROM Comment c WHERE c.video_channel_id.video.id = :videoId")
    List<Comment> getCommentByVideoId(Long videoId);
}
