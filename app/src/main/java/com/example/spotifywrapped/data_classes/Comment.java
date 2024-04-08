package com.example.spotifywrapped.data_classes;

import java.util.Date;

public class Comment {
    private String commentId;
    private String userId;
    private String text;
    private Date date;

    public Comment(String userId, String text, Date date) {
        this.userId = userId;
        this.text = text;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }
}
