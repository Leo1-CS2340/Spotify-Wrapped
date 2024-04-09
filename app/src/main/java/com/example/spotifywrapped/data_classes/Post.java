package com.example.spotifywrapped.data_classes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {

    private User user;

    private String postId;
    private String caption;
    private Date date;
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    private List<Like> likes;
    private int likeCount = 0;

    public Post(User user) {
        this.user = user;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
        comments.add(new Comment("1", "e", new Date()));
    }

    public void addLike(User user, Date date) {
        likeCount++;
        likes.add(new Like(likeCount, user.getUserId(), date));
        user.addLikedPost(this);
    }

    public void removeLike(User user) {
        for (Like l : likes) {
            if (l.getUserId().equals(user.getUserId())) {
                likes.remove(l);
                likeCount--;
                user.removeLikedPost(this);
            }
        }
    }

    public List<Like> getLikes() {
        return likes;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public User getUser() {
        return user;
    }
}
