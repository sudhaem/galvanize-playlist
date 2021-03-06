package com.galvanize.playlist.Service;

import com.galvanize.playlist.Model.Playlist;
import com.galvanize.playlist.Model.Song;
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

    public Playlist addSongToPlaylist(String playlistName, Song song) {
        Playlist foundPlaylist = repository.findByName(playlistName);
        if(foundPlaylist!=null){
            foundPlaylist.addSongToPlaylist(song);
            return foundPlaylist;
        }else{
            return null;
        }
    }

    public void deleteSongFromPlaylist(String playlistName, Song song) {
        Playlist playlist = repository.findByName(playlistName);
        if(playlist!=null) {
            playlist.getSongList().remove(song);
        }
    }

    public List<Song> getAllSongsFromPlaylist(String playlistName) {
        Playlist playlist = repository.findByName(playlistName);
        System.out.println(playlist.getSongList().size());
        return playlist.getSongList();
    }
}
