package com.galvanize.playlist.Controller;

import com.galvanize.playlist.Model.Playlist;
import com.galvanize.playlist.Service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
