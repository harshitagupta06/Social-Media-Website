package com.TweetEcho.twitterclone.controller;

import com.TweetEcho.twitterclone.service.AuthService;
import com.TweetEcho.twitterclone.dto.TokenDTO;
import com.TweetEcho.twitterclone.dto.UserDto;
import com.TweetEcho.twitterclone.request.AuthRequest;
import com.TweetEcho.twitterclone.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<TokenDTO> handleLogin(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/signup")
    ResponseEntity<UserDto> handleSignUp(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }
}
