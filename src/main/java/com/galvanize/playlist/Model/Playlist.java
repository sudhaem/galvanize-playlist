package com.galvanize.playlist.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playlistId;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Song> songList = new ArrayList<>();

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    public Playlist(){

    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Playlist(String name) {
        this.name = name;
    }

    public void addSongToPlaylist(Song song) {
        this.songList.add(song);
    }
}
