package com.medkandirou.youwatch.subscribe;


import com.medkandirou.youwatch.comment.CommentId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Subscribe {
    @EmbeddedId
    @NotNull
    private CommentId commentId;
    @NotNull private String content;
}
