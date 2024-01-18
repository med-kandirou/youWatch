package com.medkandirou.youwatch.subscribe;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SubscribeDTOres {
    private SubscribeId subscribeId;
    private LocalDate date;
}
