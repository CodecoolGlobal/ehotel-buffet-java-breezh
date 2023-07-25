package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GuestServiceImplTest {
    @Test
    void generateGuest() {
        GuestService guestService = new GuestServiceImpl();
        Guest guest = guestService.generateRandomGuest(LocalDate.parse("2023-12-01"), LocalDate.parse("2023-12-04"));
        System.out.println(guest.toString());
    }
}