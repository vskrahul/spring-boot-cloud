package com.github.vskrahul.movie_info_service.service;

import com.github.vskrahul.movie_info_service.model.MovieInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MovieInfoService {

    private List<MovieInfo> movieInfos = new ArrayList<>();

    private MovieInfo noMovieFound = new MovieInfo();

    {
        this.movieInfos.add(new MovieInfo("100", "A Man Called OTTO", "Comedy/Drama", "Tom Hank"));
        this.movieInfos.add(new MovieInfo("200", "Catch me if you can", "Crime/Comedy", "Tom Hank"));
        this.movieInfos.add(new MovieInfo("300", "Forest Gump", "Comedy/Romance", "Tom Hank"));
        this.movieInfos.add(new MovieInfo("400", "The Next Three Days", "Thriller/Action", "Russell Crowe"));
    }

    public MovieInfo getMovieInfo(String movieId) {
        Optional<MovieInfo> movieInfoOptional = this.movieInfos.stream().filter(v -> v.getMovieId().equals(movieId)).findFirst();
        log.info("[method=getMovieInfo] [movieId={}]", movieId);
        return movieInfoOptional.orElseGet(() -> noMovieFound);
    }
}
