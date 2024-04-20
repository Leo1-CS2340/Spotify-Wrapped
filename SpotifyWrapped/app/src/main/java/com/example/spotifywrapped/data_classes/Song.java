package com.example.spotifywrapped.data_classes;

public class Song {
    private final String name;
    private final String artist;
    private final String album;
    private final String preview;
    private final int popularity;

    public Song() {
        this("", "", "", "", 0);
    }
    public Song(String name, String artist, String album, String preview, int popularity) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.preview = preview;
        this.popularity = popularity;
    }
    public Song(String name) {
        this(name, "","","",0);
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getPreview() {
        return preview;
    }

    public int getPopularity() {
        return popularity;
    }
}
