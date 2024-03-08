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
    @Size(min = 1, max = 255, message = "First name must be between 1 and 255 characters")
    @NonNull private String firstname;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, max = 255, message = "Last name must be between 1 and 255 characters")
    @NonNull private String lastName;

    @PastOrPresent(message = "Creation date must be in the past or present")
    private LocalDate creationDate;

    @Size(min = 1, max = 255, message = "Profile image must be between 1 and 255 characters")
    private String profilImg;

    @Size(min = 1, max = 255, message = "Cover image must be between 1 and 255 characters")
    private String coverImg;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    @NonNull private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    @NonNull private String password;
}
