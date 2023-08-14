package com.TweetEcho.twitterclone.service;

import com.TweetEcho.twitterclone.dto.TokenDTO;
import com.TweetEcho.twitterclone.dto.UserDto;
import com.TweetEcho.twitterclone.exc.WrongCredentialsException;
import com.TweetEcho.twitterclone.request.AuthRequest;
import com.TweetEcho.twitterclone.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;
    private final ModelMapper modelMapper;

    public TokenDTO login(AuthRequest request) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            return TokenDTO
                    .builder()
                    .accessToken(tokenService.generateToken(auth))   // generating token
                    .userId(userService.findUserByUsername(request.getUsername()).getId()) // searching user in db to login
                    .username(request.getUsername())
                    .build();
        } catch (final BadCredentialsException badCredentialsException) {
            throw new WrongCredentialsException("Invalid Username or Password");
        }
    }

    public UserDto signup(RegisterRequest request) {
        return modelMapper.map(userService.create(request), UserDto.class);
    }
}
