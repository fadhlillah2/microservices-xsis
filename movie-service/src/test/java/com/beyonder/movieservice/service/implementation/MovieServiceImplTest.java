package com.beyonder.movieservice.service.implementation;

import com.beyonder.movieservice.entity.MovieEntity;
import com.beyonder.movieservice.repository.MovieRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl bookServiceImpl;

    @Mock
    private MovieRepository movieRepository;

    @Test
    void getAllTest(){
        //arrange
        MovieEntity movieEntity1 = new MovieEntity(1, "Alice", "Alice", 7.3f, "", "2023-03-26T10:00:00","2023-03-26T10:00:00");
        MovieEntity movieEntity2 = new MovieEntity(2, "Bob", "Bob", 8.3f, "", "2023-03-26T10:00:00","2023-03-26T10:00:00");
        List<MovieEntity> expected = List.of(movieEntity1, movieEntity2);
        //
        when(movieRepository.findAll()).thenReturn(expected);

        //assert
        List<MovieEntity> actual = bookServiceImpl.getAll();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void createTest(){
        //arrange
        MovieEntity movieEntity1 = new MovieEntity(1, "Alice", "Alice", 7.3f, "", "2023-03-26T10:00:00","2023-03-26T10:00:00");
        MovieEntity expected = movieEntity1;
        when(movieRepository.save(movieEntity1)).thenReturn(expected);

        //assert
        MovieEntity actual = bookServiceImpl.create(movieEntity1);
        Assertions.assertEquals(expected, actual);

    }

}
