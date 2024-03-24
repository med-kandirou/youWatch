package com.medkandirou.youwatch.channel;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ChannelDTOres {
    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate creationDate;
    private String profilImg;
    private String coverImg;
    private Integer nbrFollowers;
    private String email;
    private String password;
    private Role role;
}
