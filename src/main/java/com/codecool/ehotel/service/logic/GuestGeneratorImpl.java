package com.codecool.ehotel.service.logic;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class GuestGeneratorImpl implements GuestGenerator{

    GuestService guestService;

    public GuestGeneratorImpl(GuestService guestService) {
        this.guestService = guestService;
    }

    @Override
    public Set<Guest> generateGuests(GuestService guestService, int amount, LocalDate seasonStart, LocalDate seasonEnd) {
        Set<Guest> guestSet = new HashSet<>();
        for (int i = 0; i < amount; i++) {
            guestSet.add(guestService.generateRandomGuest(seasonStart, seasonEnd));
        }
        return guestSet;
    }
}
