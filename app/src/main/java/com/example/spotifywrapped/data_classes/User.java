package com.example.spotifywrapped.data_classes;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;

    private Wrapped wrap;
    private String email;
    private String password;
    private List<Post> posts;
    private List<User> followers;
    private List<User> following;

    public List<Post> likedPosts;

    public User(String userId, String name, Wrapped wrap) {
        this.userId = userId;
        this.name = name;
        this.wrap = wrap;
        this.posts = new ArrayList<>();
        this.likedPosts = new ArrayList<>();
    }



    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost() {
        this.posts.add(new Post(this));
    }

    public List<Post> getLikedPosts() {
        return this.likedPosts;
    }

    public void addLikedPost(Post post) {
        likedPosts.add(post);
    }
    public void removeLikedPost(Post post) {
        likedPosts.remove(post);
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public Wrapped getWrap() {
        return wrap;
    }
}
