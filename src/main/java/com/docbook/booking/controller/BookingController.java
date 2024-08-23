package com.docbook.booking.controller;

import com.docbook.booking.payload.BookingDto;
import com.docbook.booking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dockbook/booking")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBooking(@RequestParam long patientid
            , @RequestParam long consultationid){

        BookingDto bookingDto1 = bookingService.addBooking(patientid, consultationid);
        return new ResponseEntity<>(bookingDto1, HttpStatus.CREATED);
    }

}
