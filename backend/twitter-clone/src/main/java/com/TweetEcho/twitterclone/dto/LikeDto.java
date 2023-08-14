package com.TweetEcho.twitterclone.dto;

import lombok.Data;

@Data
public class LikeDto {
    private long id;
    private long userId;
    private long tweetId;
}