package com.user.service.impl;

import com.user.service.entities.Hotel;
import com.user.service.entities.Ratings;
import com.user.service.entities.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.services.HotelService;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImp.class);


    @Override
    public User saveUser(User user) {
        String randomUserId= UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

//    @Override
//    public User getUser(String userId) {
//        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User not found with given Id"));
//        Ratings[] ratingsOfUser= restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Ratings[].class);
//        logger.info("{} ",ratingsOfUser);
//
//        List<Ratings> ratings= Arrays.stream(ratingsOfUser).toList();
//
//        List<Ratings> ratingsList=ratingsOfUser.stream().map(rating->{
//           ResponseEntity<Hotel> forEntity= restTemplate.getForEntity("http://localhost:8082/hotels/"+user.getUserId(), Hotel.class);
//           Hotel hotel=forEntity.getBody();
//           logger.info("response status code:{} ",forEntity.getStatusCode());
//           rating.setHotel(hotel);
//          return rating;
//        }).collect(Collectors.toList());
//
//        user.setRatings(ratingsList);
//        return user;
//    }

    @Override
    public User getUser(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given Id"));

        Ratings[] ratingsOfUser = restTemplate.getForObject(
                "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
                Ratings[].class
        );

        logger.info("Ratings: {}", (Object) ratingsOfUser);

        List<Ratings> ratingsList = Arrays.stream(ratingsOfUser)
                .map(rating -> {

//                    ResponseEntity<Hotel> forEntity = restTemplate.getForEntity(
//                            "http://HOTELSERVICE/hotels/" + rating.getHotelId(),
//                            Hotel.class
//                    );

                    Hotel hotel = hotelService.getHotel(rating.getHotelId());

                    //logger.info("Response Status Code: {}", forEntity.getStatusCode());

                    rating.setHotel(hotel);

                    return rating;
                })
                .collect(Collectors.toList());

        user.setRatings(ratingsList);

        return user;
    }
}
