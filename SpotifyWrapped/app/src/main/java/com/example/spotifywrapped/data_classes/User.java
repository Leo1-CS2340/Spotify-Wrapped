package com.example.spotifywrapped.data_classes;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String name;
    private List<Post> posts;
    private List<User> followers;
    private List<User> following;

    public List<Post> likedPosts;

    private ArrayList<Song> topFiveSongs;
    private ArrayList<Artist> topFiveArtists;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.posts = new ArrayList<>();
        this.likedPosts = new ArrayList<>();
        this.topFiveSongs = new ArrayList<>();
        this.topFiveArtists = new ArrayList<>();

    }



    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
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

    public ArrayList<Artist> getTopFiveArtists() {
        return topFiveArtists;
    }

    public ArrayList<Song> getTopFiveSongs() {
        return topFiveSongs;
    }

    public void setTopFiveSongs(ArrayList<Song> s) {
        this.topFiveSongs = s;
    }
    public void setTopFiveArtists(ArrayList<Artist> a) {
        this.topFiveArtists = a;
    }

}
