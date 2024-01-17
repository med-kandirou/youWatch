package com.medkandirou.youwatch.channel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChannelDTOres {
    private Long id;
    private String firstname;
    private String lastName;
    private LocalDate creationDate;
    private String profilImg;
    private String coverImg;
    private String email;
    private String password;
}
