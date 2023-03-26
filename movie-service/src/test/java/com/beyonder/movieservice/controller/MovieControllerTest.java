package com.beyonder.movieservice.controller;

import com.beyonder.movieservice.entity.MovieEntity;
import com.beyonder.movieservice.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;
    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(movieController).build();
    }
    @Test
    public void testGetMovies() throws Exception {
        MovieEntity movieEntity1 = new MovieEntity(1, "Alice", "Alice", 7.3f, "", "2023-03-26T10:00:00","2023-03-26T10:00:00");
        MovieEntity movieEntity2 = new MovieEntity(2, "Bob", "Bob", 8.3f, "", "2023-03-26T10:00:00","2023-03-26T10:00:00");
        List<MovieEntity> movieEntityList = List.of(movieEntity1, movieEntity2);

        when(movieService.getAll()).thenReturn(movieEntityList);

        mockMvc.perform(MockMvcRequestBuilders.get("/Movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        "[" +
                                "{\"id\":1,\"title\":\"Alice\",\"description\":\"Alice\",\"rating\":7.3,\"image\":\"\",\"createdAt\":\"2023-03-26T10:00:00\",\"updatedAt\":\"2023-03-26T10:00:00\"}," +
                                "{\"id\":2,\"title\":\"Bob\",\"description\":\"Bob\",\"rating\":8.3,\"image\":\"\",\"createdAt\":\"2023-03-26T10:00:00\",\"updatedAt\":\"2023-03-26T10:00:00\"}" +
                                "]",
                        true // ignore array order
                ));
    }

    @Test
    public void testGeMovieById() throws Exception {

        MovieEntity movieEntity = new MovieEntity(1, "Alice", "Alice", 7.3f, "", "2023-03-26T10:00:00","2023-03-26T10:00:00");
        when(movieService.getById(1)).thenReturn(Optional.of(movieEntity));

        mockMvc.perform(MockMvcRequestBuilders.get("/Movies/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Alice"));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(7.3f));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value(""));
    }

    @Test
    public void testCreate() throws Exception {
        String movieJson = "{\"id\":1,\"title\":\"Alice\",\"description\":\"Alice\",\"rating\":7.3,\"image\":\"\",\"createdAt\":\"2023-03-26T10:00:00\",\"updatedAt\":\"2023-03-26T10:00:00\"},";

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setId(1);
        movieEntity.setTitle("Alice");
        movieEntity.setDescription("Alice");
        movieEntity.setRating(7.3f);
        movieEntity.setImage("");
        movieEntity.setCreatedAt("2023-03-26T10:00:00");
        movieEntity.setUpdatedAt("2023-03-26T10:00:00");
        MovieEntity movieEntity1 = new MovieEntity(1, "Alice", "Alice", 7.3f, "", "2023-03-26T10:00:00","2023-03-26T10:00:00");

        when(movieService.create(movieEntity)).thenReturn(movieEntity1);

        mockMvc.perform(MockMvcRequestBuilders.post("/Movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }
//
    @Test
    public void testUpdateMovie() throws Exception {
        String movieJson = "{\"id\":1,\"title\":\"Alice\",\"description\":\"Alice\",\"rating\":7.3,\"image\":\"\",\"createdAt\":\"2023-03-26T10:00:00\",\"updatedAt\":\"2023-03-26T10:00:00\"},";

        MovieEntity movieEntity1 = new MovieEntity(1, "Alice", "Alice", 7.3f, "", "2023-03-26T10:00:00","2023-03-26T10:00:00");
        when(movieService.update(any(), any())).thenReturn(movieEntity1);

        mockMvc.perform(MockMvcRequestBuilders.patch("/Movies/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Alice"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rating").value(7.3f))
                .andExpect(MockMvcResultMatchers.jsonPath("$.image").value(""));
//                .andExpect(MockMvcResultMatchers.jsonPath("$.createdAt").value("2023-03-26T10:00:00"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.updatedAt").value("2023-03-26T10:00:00"));

    }

    @Test
    public void testDeleteUser() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/Movies/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
