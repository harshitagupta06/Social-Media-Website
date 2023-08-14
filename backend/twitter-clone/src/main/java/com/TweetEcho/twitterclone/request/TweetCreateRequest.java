package com.TweetEcho.twitterclone.request;

import lombok.Getter;

@Getter
public class TweetCreateRequest {
    private String text;
    private Long userId;
}