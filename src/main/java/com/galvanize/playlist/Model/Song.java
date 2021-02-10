package com.galvanize.playlist.Model;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Song {

    @Id
    @GeneratedValue()
    private Long Id;
    private String songName;
    private String artistName;

    public Song(String songName, String artistName) {
        this.songName = songName;
        this.artistName = artistName;
    }
    public Song() {}

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
