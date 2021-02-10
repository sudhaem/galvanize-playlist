package com.galvanize.playlist.ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.playlist.Model.Playlist;
import com.galvanize.playlist.Model.Song;
import com.galvanize.playlist.Repository.PlaylistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir ="target/snippets")
public class ControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Playlist playlist;

    private List<Song> songs;

    @Autowired
    private PlaylistRepository repository;

    @BeforeEach
    public void setUp(){
        playlist = new Playlist();
        repository.deleteAll();
        songs = List.of(new Song("Hello", "Adelle"), new Song("Bye", "Amir"));

    }

    @Test
    public void test_createPlaylist() throws Exception {

        playlist = new Playlist("Wednesday jam");

        String postedJson = objectMapper.writeValueAsString(playlist);

        mockMvc
                .perform(post("/api/playlist/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(postedJson))
                .andExpect(status().isCreated())
                .andExpect(content().string("Playlist created successfully"))

                .andDo(document("addPlaylist"));

    }

    @Test
    public void createPlaylistWithExistingName() throws Exception {
        playlist = new Playlist("Wednesday jam");
        Playlist playlist2 = new Playlist("Wednesday jam");

        String postedJson = objectMapper.writeValueAsString(playlist);
        String postedJson2 = objectMapper.writeValueAsString(playlist2);

        mockMvc
                .perform(post("/api/playlist/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedJson))
                .andExpect(status().isCreated())
                .andExpect(content().string("Playlist created successfully"));

        mockMvc
                .perform(post("/api/playlist/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedJson2))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("Playlist creation is unsuccessful"))
                .andDo(document("addPlaylistWithExistingName"));
        }

    @Test
    public void test_addingPlaylistWithOutName() throws Exception {

        String postedJson = objectMapper.writeValueAsString(playlist);

        mockMvc
                .perform(post("/api/playlist/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedJson))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("Playlist Name is Required"))
                .andDo(document("addPlaylistWithNoName"));

    }
    @Test
    public void addSongToThePlaylist() throws Exception {
        Playlist playlist = new Playlist("new songs");

        String postedPlaylist = objectMapper.writeValueAsString(playlist);

        mockMvc
                .perform(post("/api/playlist/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedPlaylist))
                .andExpect(status().isCreated())
                .andExpect(content().string("Playlist created successfully"));



        Song postedSong = songs.get(0);

        String postedJson = objectMapper.writeValueAsString(postedSong);

        mockMvc
                .perform(post("/api/playlist/add-song/" + playlist.getName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.songList",hasSize(1)))
                .andExpect(jsonPath("$.songList.[0].songName").value(postedSong.getSongName()))
                .andDo(document("addSongToThePlaylist"));
    }

    @Test
    public void deleteASongFromPlaylist() throws Exception {

        Playlist playlist = new Playlist("new songs");

        String postedPlaylist = objectMapper.writeValueAsString(playlist);

        mockMvc
                .perform(post("/api/playlist/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedPlaylist))
                .andExpect(status().isCreated())
                .andExpect(content().string("Playlist created successfully"));



        Song postedSong = songs.get(0);

        String postedJson = objectMapper.writeValueAsString(postedSong);

        mockMvc
                .perform(post("/api/playlist/add-song/" + playlist.getName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedJson))
                .andExpect(status().isOk());

        Song postedSong2 = songs.get(1);

        String postedJson2 = objectMapper.writeValueAsString(postedSong2);

        mockMvc
                .perform(post("/api/playlist/add-song/" + playlist.getName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedJson2))
                .andExpect(status().isOk());

        mockMvc
                .perform(delete("/api/playlist/delete-song/" + playlist.getName())
                .contentType(MediaType.APPLICATION_JSON)
                        .content(postedJson2))
                .andExpect(status().isOk())
                .andDo(document("deleteSongFromPlaylist"));



    }

    @Test
    public void getAllSongsFromPlaylist() throws Exception {

        Playlist playlist = new Playlist("new songs");

        String postedPlaylist = objectMapper.writeValueAsString(playlist);

        mockMvc
                .perform(post("/api/playlist/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedPlaylist))
                .andExpect(status().isCreated())
                .andExpect(content().string("Playlist created successfully"));



        Song postedSong = songs.get(0);

        String postedJson = objectMapper.writeValueAsString(postedSong);

        mockMvc
                .perform(post("/api/playlist/add-song/" + playlist.getName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedJson))
                .andExpect(status().isOk());

        Song postedSong2 = songs.get(1);

        String postedJson2 = objectMapper.writeValueAsString(postedSong2);

        mockMvc
                .perform(post("/api/playlist/add-song/" + playlist.getName())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postedJson2))
                .andExpect(status().isOk());

        mockMvc
                .perform(get("/api/playlist/get-songs/" + playlist.getName()))
                .andExpect(status().isOk())
                .andDo(document("getAllSongsFromPlaylist"));


    }

}
