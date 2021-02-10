package com.galvanize.playlist.ControllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.playlist.Model.Playlist;
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

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @BeforeEach
    public void setUp(){
        playlist = new Playlist();
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

}
