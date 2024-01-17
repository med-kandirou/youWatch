package com.medkandirou.youwatch.comment;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Comment {
    @EmbeddedId
    @NotNull private CommentId commentId;
    @NotNull private String content;
}
