package com.beyonder.movieservice.controller;

import com.beyonder.movieservice.entity.MovieEntity;
import com.beyonder.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Movies")
@RequiredArgsConstructor
@Slf4j
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public List<MovieEntity> getAll() {
        return movieService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<MovieEntity> getById(@PathVariable Integer id) {
        return movieService.getById(id);
    }

    @GetMapping("/getByTitle")
    public List<MovieEntity> getByTitle(
            Model model,
            @RequestParam(value = "keyWord", defaultValue = "") String keyWord){
        List<MovieEntity> listMovies = movieService.listAllByTitle(keyWord);
        model.addAttribute("listUsers", listMovies);
        model.addAttribute("keyWord", keyWord);
        return listMovies;
    }


    @PostMapping
    public MovieEntity create(@RequestBody MovieEntity movieEntity) {
        return movieService.create(movieEntity);
    }

    @PatchMapping("/{id}")
    public MovieEntity update(@RequestBody MovieEntity movieEntity, @PathVariable Integer id) {
        return movieService.update(movieEntity, id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Integer id) {
        movieService.deleteById(id);
        return "Successfully deleted!";
    }
}
