package com.projet.library.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dates")
public class DateController {
    
    @GetMapping("/date")
    public Date myDate(){
        Date now = new Date();
        return now;
    }

    @GetMapping("/localDateTime")
    public String myLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }
}
