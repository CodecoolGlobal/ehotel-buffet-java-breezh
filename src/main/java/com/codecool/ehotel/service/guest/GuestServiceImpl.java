package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuestServiceImpl implements GuestService{
    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        return null;
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        Set<Guest> guestsForDay = new HashSet<>();
        for (Guest guest : guests) {
            if(guest.checkIn().compareTo(date) * guest.checkOut().compareTo(date) > 0) {
                guestsForDay.add(guest);
            }
        }
        return guestsForDay;
    }
}
