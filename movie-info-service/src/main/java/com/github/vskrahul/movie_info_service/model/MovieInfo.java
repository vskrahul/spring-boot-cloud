package com.github.vskrahul.movie_info_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieInfo {
    private String movieId;
    private String name;
    private String genre;
    private String actor;
}
