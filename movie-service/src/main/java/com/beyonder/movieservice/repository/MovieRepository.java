package com.beyonder.movieservice.repository;

import com.beyonder.movieservice.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {
    public List<MovieEntity> findByTitleContains(String keyWord);
}
