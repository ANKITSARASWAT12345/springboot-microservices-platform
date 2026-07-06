package com.hotel.HotelService.exceptions;

public class ResourseNotFoundException extends RuntimeException {
    public ResourseNotFoundException(String s) {
        super(s);
    }

    public ResourseNotFoundException(){
        super("Resource not sound !!");
    }
}
