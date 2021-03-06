package com.galvanize.playlist.Controller;

import com.galvanize.playlist.Model.Playlist;
import com.galvanize.playlist.Model.Song;
import com.galvanize.playlist.Service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService service;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addPlaylist(@RequestBody  Playlist playlist){
        return this.service.addPlaylist(playlist);
    }

    @PostMapping("/add-song/{playlistName}")
    public Playlist addSongToPlaylist(@PathVariable String playlistName, @RequestBody Song song){
        return this.service.addSongToPlaylist(playlistName, song);
    }

    @DeleteMapping("/delete-song/{playlistName}")
    public void deleteSong(@PathVariable String playlistName, @RequestBody Song song){
        this.service.deleteSongFromPlaylist(playlistName,song);
    }

    @GetMapping("/get-songs/{playlistName}")
    public List<Song> getSongsFromPlaylist(@PathVariable String playlistName){
        return this.service.getAllSongsFromPlaylist(playlistName);
    }



}
