package com.medkandirou.youwatch.security.auth;

import com.medkandirou.youwatch.channel.Channel;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
  @NonNull private String token;
  @NonNull private Channel channel;
}
