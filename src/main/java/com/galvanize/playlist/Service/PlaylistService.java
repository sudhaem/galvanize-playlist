package com.galvanize.playlist.Service;

import com.galvanize.playlist.Model.Playlist;
import com.galvanize.playlist.Repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository repository;


    public ResponseEntity<String> addPlaylist(Playlist playlist) {
        List<Playlist> allPlaylist = repository.findAll();

        if(playlist.getName()==null){
            return new ResponseEntity<>("Playlist Name is Required",HttpStatus.NOT_ACCEPTABLE);
        }

        for (Playlist p: allPlaylist) {
            if(p.getName().equalsIgnoreCase(playlist.getName())) {
                return new ResponseEntity<String>("Playlist creation is unsuccessful", HttpStatus.NOT_ACCEPTABLE);
            }

        }
        repository.save(playlist);

        return new ResponseEntity<>("Playlist created successfully", HttpStatus.CREATED);
    }
}
