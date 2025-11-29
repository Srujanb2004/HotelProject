package com.example.hotel.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HotelController {

    @GetMapping()
    public String display(){
        return "HelloWorld";

    }
}
