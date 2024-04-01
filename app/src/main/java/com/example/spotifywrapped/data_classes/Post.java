package com.example.spotifywrapped.data_classes;
import java.util.Date;
import java.util.List;

public class Post {
    private String postId;
    private String caption;
    private Date date;
    private List<Comment> comments;
    private List<Like> likes;
    private int likeCount = 0;
    public void addLike(User user, Date date) {
        likeCount++;
        likes.add(new Like(likeCount, user.getUserId(), date));
    }
    public void removeLike() {
        likeCount--;
    }
}
