package com.github.vskrahul.moviecatalogservice.service;

import com.github.vskrahul.moviecatalogservice.client.MovieInfoClient;
import com.github.vskrahul.moviecatalogservice.client.MovieRatingClient;
import com.github.vskrahul.moviecatalogservice.model.CatalogItem;
import com.github.vskrahul.moviecatalogservice.model.CatalogItems;
import com.github.vskrahul.moviecatalogservice.model.Movie;
import com.github.vskrahul.moviecatalogservice.model.UserRating;
import com.github.vskrahul.moviecatalogservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MovieCatalogService {

    private MovieInfoClient movieInfoClient;
    private MovieRatingClient movieRatingClient;
    
    @Autowired
    public MovieCatalogService(MovieInfoClient movieInfoClient, MovieRatingClient movieRatingClient) {
        this.movieInfoClient = movieInfoClient;
        this.movieRatingClient = movieRatingClient;
        String v = System.getProperty("sun.net.client.defaultConnectTimeout");
    }

    public CatalogItems catalogItems(String userId) {

        CatalogItems items = new CatalogItems();
        UserRating ratings = this.movieRatingClient.getRatings(userId);

        log.info("[method=catalogItems] [responseBody={}]", JsonUtil.toJsonString(ratings));
        
        List<CatalogItem> catalogs = ratings.getRatings().stream().map(r -> {
            Movie m = this.movieInfoClient.getMovie(r.getMovieId());
            return CatalogItem.builder()
                    .name(m.getName())
                    .genre(m.getGenre())
                    .actor(m.getActor())
                    .rating(r.getRating())
                    .build();
        }).collect(Collectors.toList());
        items.setCatalogItems(catalogs);
        return items;
    }
}
