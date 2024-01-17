package com.medkandirou.youwatch.comment;

import com.medkandirou.youwatch.helpers.Video_channel_Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Video_channel_Id> {
}
