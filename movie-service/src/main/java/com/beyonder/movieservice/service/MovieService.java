package com.beyonder.movieservice.service;

import com.beyonder.movieservice.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<MovieEntity> getAll() ;

    Optional<MovieEntity> getById(Integer id);

    MovieEntity create(MovieEntity movieEntity);

    MovieEntity update(MovieEntity movieEntity, Integer id);

    void deleteById(Integer id);

    List<MovieEntity> listAllByTitle(String keyWord);

}
