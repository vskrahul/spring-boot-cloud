package com.github.vskrahul.ratingdataservice.client;

import com.github.vskrahul.ratingdataservice.model.Rating;
import com.github.vskrahul.ratingdataservice.model.UserRating;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Slf4j
public class RatingDataClient {

    private List<Rating> ratings = new ArrayList<>();

    {
        this.ratings.add(new Rating("100", 4));
        this.ratings.add(new Rating("200", 4));
        this.ratings.add(new Rating("300", 4));
        this.ratings.add(new Rating("400", 4));
    }

//    @HystrixCommand(fallbackMethod = "getRatingsFallback",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
//    })
    public UserRating getRatings(String userId) {
        log.info("[method=getRatings] [userId={}]", userId);
        return new UserRating(userId, this.ratings);
    }

//    @HystrixCommand(fallbackMethod = "getMovieRatingFallback",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
//    })
    public Rating getMovieRating(String movieId) {
        return new Rating(movieId, 4);
    }

    public Rating getMovieRatingFallback(String movieId) {
        return new Rating(movieId, 4);
    }

    public UserRating getRatingsFallback(String userId) {
        return new UserRating(userId, Arrays.asList(
                new Rating("100", 2),
                new Rating("200", 3)
        ));
    }
}