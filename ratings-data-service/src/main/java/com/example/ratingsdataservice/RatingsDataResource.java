package com.example.ratingsdataservice;

import com.example.ratingsdataservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/ratingsdata")

public class RatingsDataResource {
    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,4);
    }

    @RequestMapping("users/{userId}")
    public Rating getRating(@PathVariable("userId") String userId){
        //return new Rating(movieId,4);
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",4),
                new Rating()
        )
    }


}

//Do we have to assume that rating data service provides all the movies which is rated by a particular user?


