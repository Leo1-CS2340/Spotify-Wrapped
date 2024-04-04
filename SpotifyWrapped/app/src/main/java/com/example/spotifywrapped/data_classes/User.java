package com.example.spotifywrapped.data_classes;

import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private String name;
    private String profile_picture;
    private String spotify_id;
    private ArrayList<Song> topFiveSongs;
    private ArrayList<Artist> topFiveArtists;

    public User(DocumentSnapshot userDocument) {
        this.name = (String) userDocument.get("name");
        this.profile_picture = (String) userDocument.get("profile_picture");
        this.spotify_id = (String) userDocument.get("spotify_id");
        this.topFiveSongs = (ArrayList<Song>) userDocument.get("topFiveSongs");
        this.topFiveArtists = (ArrayList<Artist>) userDocument.get("topFiveArtists");

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
