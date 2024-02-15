package com.medkandirou.youwatch.security.auth;


import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final ChannelRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    Channel channel= new Channel();
    channel.setFirstname(request.getFirstname());
    channel.setLastName(request.getLastname());
    channel.setEmail(request.getEmail());
    channel.setCreationDate(LocalDate.now());
    channel.setPassword(passwordEncoder.encode(request.getPassword()));
    channel.setRole(request.getRole());
    repository.save(channel);
    String jwt=jwtService.generateToken(channel);
    return AuthenticationResponse.builder().Token(jwt).build();
  }

  public AuthenticationResponse login(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    Channel channel=repository.findByEmail(request.getEmail())
            .orElseThrow(()-> new UsernameNotFoundException("Channel not found"));
    Map<String, Object> claims = new HashMap<>();
    claims.put("firstname", channel.getUsername());
    claims.put("lastname", channel.getLastName());
    claims.put("password", channel.getPassword());
    claims.put("role", channel.getRole().name());

    String jwt=jwtService.generateToken(claims,channel);
    return AuthenticationResponse.builder().Token(jwt).build();
  }

}
