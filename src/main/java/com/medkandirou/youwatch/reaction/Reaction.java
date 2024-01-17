package com.medkandirou.youwatch.reaction;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@Entity
public class Reaction {
    @EmbeddedId
    @NotNull private ReactionId reactionId;
    @NotNull private boolean reaction;
}
