package com.rating.RatingService.services;

import com.rating.RatingService.entities.Ratings;
import com.rating.RatingService.repository.RatingRepository;

import java.util.List;

public interface RatingService {

    Ratings create(Ratings rating);

    List<Ratings> getRatings();

    List<Ratings> getRatingsByUserId(String UserId);

    List<Ratings> getRatingByHotelId(String hotelId);

}
