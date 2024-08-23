package com.docbook.booking.service;

import com.docbook.booking.payload.BookingDto;

public interface BookingService {

    public BookingDto addBooking(long patientid, long consultationid);
}
