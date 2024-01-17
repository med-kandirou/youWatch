package com.medkandirou.youwatch.channel;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ChannelDTOreq {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "First name cannot be null")
    @Size(min = 1, max = 255, message = "First name must be between 1 and 255 characters")
    private String firstname;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 1, max = 255, message = "Last name must be between 1 and 255 characters")
    private String lastName;

    @NotNull(message = "Creation date cannot be null")
    @PastOrPresent(message = "Creation date must be in the past or present")
    private LocalDate creationDate;

    @NotNull(message = "Profile image cannot be null")
    @Size(min = 1, max = 255, message = "Profile image must be between 1 and 255 characters")
    private String profilImg;

    @NotNull(message = "Cover image cannot be null")
    @Size(min = 1, max = 255, message = "Cover image must be between 1 and 255 characters")
    private String coverImg;

    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password;
}
