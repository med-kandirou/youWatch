package com.medkandirou.youwatch.channel;


import com.medkandirou.youwatch.video.Video;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Channel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull private String firstname;
    @NonNull private String lastname;
    @NonNull private LocalDate creationDate;
    @NonNull private String profilImg;
    @NonNull private String coverImg;
    @NonNull private Integer nbrFollowers;
    @NonNull private String email;
    @NonNull private String password;
    @Enumerated(EnumType.STRING)
    @NonNull private Role role;

    //favories
    @ManyToMany(mappedBy = "channels")
    private Set<Video> videos = new HashSet<>();


    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.role));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
