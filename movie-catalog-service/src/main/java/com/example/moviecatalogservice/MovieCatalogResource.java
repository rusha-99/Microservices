package com.example.moviecatalogservice;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

      //   restTemplate = new RestTemplate();



        // We assume this the response we get from ratings data service
        List<Rating> ratings = Arrays.asList(
                new Rating("1234",3),
                new Rating("2345",5),
                new Rating("2345",5)

        );


        return ratings.stream().map(rating -> {
            //If we use resttemplate we use it like in below
            Movie movie=restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

            //If we use WebClient in Synchronous manner
//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
//            System.out.println(movie);
            return new CatalogItem(movie.getName(),"Test",rating.getRating());
        })
        .collect(Collectors.toList());



    }
}


//get all rated movie IDs


//For each movie Id, call movie info service and get details


//Put them all together



//        return Collections.singletonList(
//                new CatalogItem("Titanic", "Test",4)
//        );