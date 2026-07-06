package com.rating.RatingService.impl;

import com.rating.RatingService.entities.Ratings;
import com.rating.RatingService.repository.RatingRepository;
import com.rating.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {


    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Ratings create(Ratings rating){
      return ratingRepository.save(rating);
    }

    @Override
    public List<Ratings> getRatings(){
        return ratingRepository.findAll();
    }

    @Override
    public  List<Ratings> getRatingsByUserId(String userId){
        return ratingRepository.findByUserId(userId);
    }


    @Override
    public  List<Ratings> getRatingByHotelId(String hotelId){
        return ratingRepository.findByHotelId(hotelId);
    }
}
