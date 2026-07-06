package com.rating.RatingService.controllers;

import com.rating.RatingService.entities.Ratings;
import com.rating.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {


    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<Ratings> create(@RequestBody Ratings ratings){
       return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(ratings));
    }

    @GetMapping
    public  ResponseEntity<List<Ratings>> getRatings(){
        return ResponseEntity.ok(ratingService.getRatings());
    }

    @GetMapping("/users/{userId}")
    public  ResponseEntity<List<Ratings>> getRatingsByUserId(@PathVariable  String userId){
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }
    @GetMapping("/hotels/{hotelId}")
    public  ResponseEntity<List<Ratings>> getRatingsByHotelId(@PathVariable  String hotelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }





}
