package com.user.service.controllers;

import com.user.service.entities.User;
import com.user.service.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
          User user1=userService.saveUser(user);
          return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount=1;

    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable  String userId){

        log.info("Retrycount:{} ", retryCount);
        retryCount++;
       User user= userService.getUser(userId);
       return  ResponseEntity.ok(user);
    }

    //fall back method for the circuit breaker


    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){


        User user=User.builder().
                email("dummyuser@hmail.com")
                .name("Dummy")
                .about("This dummy user is created becuase some  services are down")
                .userId("1234")
                .build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser= userService.getAllUser();
        return  ResponseEntity.ok(allUser);
    }

}
