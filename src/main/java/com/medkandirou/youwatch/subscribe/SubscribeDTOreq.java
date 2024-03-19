package com.medkandirou.youwatch.subscribe;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;


@Data
public class SubscribeDTOreq {
    @NotNull(message = "subscribeId is required")
    private SubscribeId subscribeId;
}
