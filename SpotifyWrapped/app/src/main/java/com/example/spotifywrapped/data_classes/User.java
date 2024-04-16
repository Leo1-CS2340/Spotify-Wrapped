package com.example.spotifywrapped.data_classes;

import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public ArrayList<Artist> getTopFiveArtists() {
        ArrayList<Artist> totalArtists = new ArrayList<>();
        Object[] temp2 = topFiveArtists.toArray();


        for (int i = 0; i < temp2.length; i++) {
            HashMap<String, Object> temp = (HashMap<String, Object>) temp2[i];
            ArrayList<Object> list = new ArrayList<Object>(temp.values());
            Artist art = new Artist((String) list.get(2), (String) list.get(3), (List<String>) list.get(0), ((Long) list.get(1)).intValue());
            totalArtists.add(art);
        }
        topFiveArtists = totalArtists;
        return topFiveArtists;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public String getSpotify_id() {
        return spotify_id;
    }

    public ArrayList<Song> getTopFiveSongs() {
        ArrayList<Song> totalSongs = new ArrayList<>();
        Object[] temp2 = topFiveSongs.toArray();
//        HashMap<String, Object> temp = (HashMap<String, Object>) temp2[0];
//
//
//        ArrayList<Object> list = new ArrayList<Object>(temp.values());
//        System.out.println(list.get(0)); //mp3 link
//        System.out.println(list.get(1)); //artist name
//        System.out.println(list.get(2)); //album name
//        System.out.println(list.get(3)); //popularity
//        System.out.println(list.get(4)); //song name


        for (int i = 0; i < temp2.length; i++) {
            HashMap<String, Object> temp = (HashMap<String, Object>) temp2[i];
            ArrayList<Object> list = new ArrayList<Object>(temp.values());
            Song sg = new Song((String) list.get(4), (String) list.get(1), (String) list.get(2), (String) list.get(0), ((Long) list.get(3)).intValue());
            totalSongs.add(sg);
        }
        topFiveSongs = totalSongs;

        return topFiveSongs;
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
