package com.galvanize.playlist.ServiceTest;

import com.galvanize.playlist.Model.Playlist;
import com.galvanize.playlist.Repository.PlaylistRepository;
import com.galvanize.playlist.Service.PlaylistService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceUnitTest {

    @Mock
    private PlaylistRepository repository;

    @InjectMocks
    private PlaylistService service;

    @Test
    public void addPlaylist(){

        Playlist playlist = new Playlist("funk");

        when(repository.save(any(Playlist.class))).thenReturn(playlist);

        String actualResponse = service.addPlaylist(playlist).getBody();

        assertEquals("Playlist created successfully" , actualResponse);

        verify(repository,times(1)).save(playlist);
    }
    @Test
    public void addPlaylistWithExistingName(){

        Playlist playlist = new Playlist("funk");
        Playlist playlist2 = new Playlist("funk");

        List<Playlist> responsePlaylist = List.of(playlist);


        when(repository.findAll()).thenReturn(responsePlaylist);

        String actualResponse = service.addPlaylist(playlist2).getBody();

        assertEquals("Playlist creation is unsuccessful" , actualResponse);

        verify(repository,times(1)).findAll();
    }

    @Test
    public void addPlaylistWithNoName(){

        Playlist playlist = new Playlist();

        String actualResponse = service.addPlaylist(playlist).getBody();

        assertEquals("Playlist Name is Required" , actualResponse);

    }

}
