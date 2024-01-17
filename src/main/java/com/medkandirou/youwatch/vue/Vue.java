package com.medkandirou.youwatch.vue;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;


@Data
@RequiredArgsConstructor
@Entity
public class Vue {
    @EmbeddedId
    private VueId vueId;
    private LocalDate date;
}
