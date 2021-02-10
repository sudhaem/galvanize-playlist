package com.galvanize.playlist.Service;

import com.galvanize.playlist.Model.Playlist;
import com.galvanize.playlist.Repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository repository;


    public String addPlaylist(Playlist playlist) {
        repository.save(playlist);
        return "Playlist created successfully";
    }
}
