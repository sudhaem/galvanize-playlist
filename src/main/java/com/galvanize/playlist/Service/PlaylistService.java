package com.galvanize.playlist.Service;

import com.galvanize.playlist.Model.Playlist;
import com.galvanize.playlist.Repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository repository;


    public String addPlaylist(Playlist playlist) {
        List<Playlist> allPlaylist = repository.findAll();

        for (Playlist p: allPlaylist) {
            if(p.getName().equalsIgnoreCase(playlist.getName())) {
                return "Playlist creation is unsuccessful";
            }

        }
        repository.save(playlist);

        return "Playlist created successfully";
    }
}
