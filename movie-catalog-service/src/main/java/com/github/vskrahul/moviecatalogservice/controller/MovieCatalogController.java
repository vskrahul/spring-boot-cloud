package com.github.vskrahul.moviecatalogservice.controller;

import com.github.vskrahul.moviecatalogservice.model.CatalogItems;
import com.github.vskrahul.moviecatalogservice.service.MovieCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

//@Api
@RestController
@RequestMapping("catalog")
@RefreshScope
public class MovieCatalogController {

    private MovieCatalogService movieCatalogService;
    @Value("${config}")
    private String config;

    @Autowired
    public MovieCatalogController(MovieCatalogService movieCatalogService) {
        this.movieCatalogService = movieCatalogService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "{userId}")
    public CatalogItems getCatalog(@PathVariable String userId) {
        return this.movieCatalogService.catalogItems(userId);
    }

    @GetMapping("config")
    public String config() {
        return this.config;
    }
}
