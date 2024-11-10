package com.github.vskrahul.moviecatalogservice.client;

import com.github.vskrahul.moviecatalogservice.model.Rating;
import com.github.vskrahul.moviecatalogservice.model.UserRating;
import com.github.vskrahul.moviecatalogservice.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Repository
public class MovieRatingClient {

    private RestTemplate restTemplate;

    @Autowired
    public MovieRatingClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Rating getRating(String movieId) {
        UriComponents uriComponents = UriComponentsBuilder
                                                .fromUriString("http://RATING-DATA-SERVICE/ratingdata/{movieId}")
                                                .build();
        Rating rating = null;
        try {
            rating = restTemplate.getForObject(uriComponents.expand(movieId).toUri(), Rating.class);

            log.info("[method=getRating] [movieId={}] [responseBody={}]",
                    movieId,
                    JsonUtil.toJsonString(rating));
        } catch(RestClientException e) {
            log.error("[method=getRating] [errorMessage={}]", e.getMessage());
        } catch(Exception e) {
            log.error("[method=getRating] [errorMessage={}]", e.getMessage());
        }
        return rating;
    }

//    @HystrixCommand(fallbackMethod = "getFallbackRatings",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
//                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
//                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50")
//            })
    public UserRating getRatings(String userId) {
        UriComponents uriComponents = UriComponentsBuilder
                                        .fromUriString("http://RATING-DATA-SERVICE/ratingdata/users/{userId}")
                                        .build();
        URI uri = uriComponents.expand(userId).toUri();
        LocalDateTime startTime = LocalDateTime.now();
        UserRating rating = null;
        try {
            rating = restTemplate.getForObject(uri, UserRating.class);

            log.info("[method=getRating] [userId={}] [duration={}] [responseBody={}]",
                    userId,
                    Duration.between(startTime, LocalDateTime.now()),
                    JsonUtil.toJsonString(rating));
        } catch(RestClientException e) {
            log.error("[method=getRating] [duration={}] [errorMessage={}]",
                    Duration.between(startTime, LocalDateTime.now()),
                    e.getMessage());
        } catch (Exception e) {
            log.error("[method=getRating] [duration={}] [errorMessage={}]",
                    Duration.between(startTime, LocalDateTime.now()),
                    e.getMessage());
        }
        return rating;
    }

    public UserRating getFallbackRatings(String userId) {
        UserRating rating = new UserRating(userId, Arrays.asList(
                new Rating("0", 0)
        ));
        log.error("[method=getFallbackRatings]");
        return rating;
    }
}
