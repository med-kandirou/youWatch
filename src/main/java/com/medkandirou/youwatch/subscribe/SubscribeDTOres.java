package com.medkandirou.youwatch.subscribe;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class SubscribeDTOres {
    private SubscribeId subscribeId;
    private LocalDateTime date;
}
