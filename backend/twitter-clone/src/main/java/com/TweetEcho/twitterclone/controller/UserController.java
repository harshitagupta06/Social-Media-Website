package com.TweetEcho.twitterclone.controller;

import com.TweetEcho.twitterclone.dto.LikeDto;
import com.TweetEcho.twitterclone.dto.TweetDto;
import com.TweetEcho.twitterclone.dto.UserDto;
import com.TweetEcho.twitterclone.request.UpdateUserRequest;
import com.TweetEcho.twitterclone.service.FileService;
import com.TweetEcho.twitterclone.service.LikeService;
import com.TweetEcho.twitterclone.service.TweetService;
import com.TweetEcho.twitterclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TweetService tweetService;
    private final LikeService likeService;
    private final FileService fileService;

    @GetMapping
    ResponseEntity<Page<UserDto>> getUsers(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(200).body(userService.getUsers(page));
    }

    @GetMapping("/{username}")
    ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.status(200).body(userService.getUserByUsername(username));
    }

    @PutMapping("/{id}")
    ResponseEntity<UserDto> updateUserById(@PathVariable Long id,
                                           @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.status(200).body(userService.updateUserById(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tweets")
    ResponseEntity<Page<TweetDto>> getUsersTweetsByUserId(@PathVariable Long id,
                                                          @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(200).body(tweetService.getUsersTweetsByUserId(id, page));
    }

    @GetMapping("/{id}/likes")
    ResponseEntity<Page<LikeDto>> getUsersLikesByUserId(@PathVariable Long id,
                                                        @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable page) {
        return ResponseEntity.status(200).body(likeService.getUsersLikesByUserId(id, page));
    }

    @PostMapping(
            value = "/{username}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> uploadUserProfileImage(@PathVariable String username,
                                                @RequestParam("file") MultipartFile file) {
        fileService.uploadProfileImage(username, file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}/image/download")
    ResponseEntity<byte[]> downloadUserProfileImage(@PathVariable String username) {
        return ResponseEntity.ok(fileService.downloadProfileImage(username));
    }
}