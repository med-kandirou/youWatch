package com.medkandirou.youwatch.channel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ChannelDTOreq {
    private Long id;

    @NotNull(message = "First name cannot be null")
    private String firstname;

    @NotNull(message = "Last name cannot be null")
    private String lastname;

    private String profilImg;

    private String coverImg;

    @NotNull(message = "Email cannot be null")
    private String email;
    private String password;
}
