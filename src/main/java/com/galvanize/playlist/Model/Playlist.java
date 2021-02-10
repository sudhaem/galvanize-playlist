package com.galvanize.playlist.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playlistId;
    private String name;

    public Playlist(){

    }
    public Playlist(String name) {
        this.name = name;
    }
}
