package com.example.spotifywrapped.data_classes;

import java.util.ArrayList;
import java.util.List;

public class Wrapped {
    private List<String>topArtists;
    private List<String> topSongs;
    private List<String> minutesListened;
    private List<String> topGenre;

    public Wrapped(List<String> topArtists, List<String> topSongs, List<String> minutesListened, List<String> topGenre) {
        this.topArtists = topArtists;
        this.topSongs = topSongs;
        this.minutesListened = minutesListened;
        this.topGenre = topGenre;
    }

    public List<String> getTopArtists() {
        return topArtists;
    }

    public List<String> getTopSongs() {
        return topSongs;
    }

    public List<String> getMinutesListened() {
        return minutesListened;
    }

    public List<String> getTopGenre() {
        return topGenre;
    }
}

