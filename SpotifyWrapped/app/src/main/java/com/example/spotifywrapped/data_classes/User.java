package com.example.spotifywrapped.data_classes;
import java.util.ArrayList;
import java.util.List;
import com.google.firebase.firestore.DocumentSnapshot;

public class User {
    private String profile_picture;
    private ArrayList<Song> topFiveSongs;
    private ArrayList<Artist> topFiveArtists;

    private String spotify_id;
    private String name;
    private List<Post> posts;
    private List<User> followers;
    private List<User> following;

    public List<Post> likedPosts;

    private boolean visibility;

    public User(String userId, String name) {
        this.spotify_id = userId;
        this.name = name;
        this.posts = new ArrayList<>();
        this.likedPosts = new ArrayList<>();
        this.topFiveSongs = new ArrayList<>();
        this.topFiveArtists = new ArrayList<>();

    }

    public User(DocumentSnapshot userDocument) {
        this.name = (String) userDocument.get("name");
        this.profile_picture = (String) userDocument.get("profile_picture");
        this.spotify_id = (String) userDocument.get("spotify_id");
        this.topFiveSongs = (ArrayList<Song>) userDocument.get("topFiveSongs");
        this.topFiveArtists = (ArrayList<Artist>) userDocument.get("topFiveArtists");

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

    public String getProfile_picture() {
        return profile_picture;
    }

    public String getSpotify_id() {
        return spotify_id;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", profile_picture='" + profile_picture + '\'' +
                ", spotify_id='" + spotify_id + '\'' +
                ", topFiveSongs=" + topFiveSongs +
                ", topFiveArtists=" + topFiveArtists +
                '}';
    }
}
