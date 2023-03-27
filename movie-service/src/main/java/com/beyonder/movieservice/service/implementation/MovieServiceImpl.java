package com.beyonder.movieservice.service.implementation;

import com.beyonder.movieservice.entity.MovieEntity;
import com.beyonder.movieservice.exception.MovieNotFoundException;
import com.beyonder.movieservice.repository.MovieRepository;
import com.beyonder.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }


    @Override
    public Optional<MovieEntity> getById(Integer id) {
        return Optional.ofNullable(movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id)));
    }

    @Override
    public MovieEntity create(MovieEntity movieEntity) {
        return movieRepository.save(movieEntity);
    }

    @Override
    public MovieEntity update(MovieEntity movieEntity, Integer id) {
        MovieEntity movieEntity1 = movieRepository.findById(id).get();
        movieEntity1.setTitle(movieEntity.getTitle());
        movieEntity1.setDescription(movieEntity.getDescription());
        movieEntity1.setRating(movieEntity.getRating());
        movieEntity1.setImage(movieEntity.getImage());
        return movieRepository.save(movieEntity1);
    }

    @Override
    public void deleteById(Integer id) {
        movieRepository.deleteById(id);
    }

    @Override
    public List<MovieEntity> listAllByTitle(String keyWord){
        if(keyWord != null){
            return (List<MovieEntity>) movieRepository.findByTitleContains(keyWord);
        } else {
            return (List<MovieEntity>) movieRepository.findAll();
        }

    }

}
