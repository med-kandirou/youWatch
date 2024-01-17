package com.medkandirou.youwatch.subscribe;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Entity
public class Subscribe {
    @EmbeddedId
    @NotNull private SubscribeId subscribeId;
    @NotNull private LocalDate date;
}
