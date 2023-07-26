package com.codecool.ehotel.service.logic;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.guest.GuestService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface GuestGenerator {
    public Set<Guest> generateGuests (GuestService guestService, int amount, LocalDate seasonStart, LocalDate seasonEnd);
}
