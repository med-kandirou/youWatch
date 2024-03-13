package com.medkandirou.youwatch.security.auth;


import com.medkandirou.youwatch.channel.Channel;
import com.medkandirou.youwatch.channel.ChannelDTOres;
import com.medkandirou.youwatch.channel.ChannelRepository;
import com.medkandirou.youwatch.exception.ResourceNotFoundException;
import com.medkandirou.youwatch.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
  private final ModelMapper modelMapper;

  public ChannelDTOres register(RegisterRequest request) {
    Channel channel= new Channel();
    channel.setFirstname(request.getFirstname());
    channel.setLastname(request.getLastname());
    channel.setEmail(request.getEmail());
    channel.setCreationDate(LocalDate.now());
    channel.setProfilImg("https://flowbite.com/docs/images/people/profile-picture-3.jpg");
    channel.setCoverImg("https://flowbite.com/docs/images/examples/image-3@2x.jpg");
    channel.setPassword(passwordEncoder.encode(request.getPassword()));
    channel.setRole(request.getRole());
    return modelMapper.map(repository.save(channel), ChannelDTOres.class);
  }

  public AuthenticationResponse login(AuthenticationRequest request) {

    Channel channel=repository.findByEmail(request.getEmail())
            .orElseThrow(()-> new ResourceNotFoundException("Channel not found"));
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    Map<String, Object> claims = new HashMap<>();
    claims.put("firstname", channel.getUsername());
    claims.put("lastname", channel.getLastname());
    claims.put("password", channel.getPassword());
    claims.put("role", channel.getRole().name());

    String jwt=jwtService.generateToken(claims,channel);
    return AuthenticationResponse.builder().Token(jwt).build();
  }

}
