package com.github.vskrahul.movie_info_service.controller;


import com.github.vskrahul.movie_info_service.model.MovieInfo;
import com.github.vskrahul.movie_info_service.service.MovieInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MovieInfoController {

    MovieInfoService movieInfoService;
    @Autowired
    public MovieInfoController(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @GetMapping("movie-info/{movieId}")
    public ResponseEntity<MovieInfo> getMovieInfo(@PathVariable("movieId") String movieId) {
        return ResponseEntity.ok(this.movieInfoService.getMovieInfo(movieId));
    }
}
