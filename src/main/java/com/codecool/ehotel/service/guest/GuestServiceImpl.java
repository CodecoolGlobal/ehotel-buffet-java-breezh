package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GuestServiceImpl implements GuestService{
    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        Random random = new Random();

        int randomWithNextIntWithinARange = random.nextInt(100 - 1) + 1;
        String name = "guest" + randomWithNextIntWithinARange;

        GuestType guestType = GuestType.values()[random.nextInt(GuestType.values().length)];

        int randomNumberOfNights = random.nextInt(7) + 1;
        long minDay = seasonStart.toEpochDay();
        long maxDay = seasonEnd.toEpochDay();
        long randCheckInDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate checkIn = LocalDate.ofEpochDay(randCheckInDay);
        LocalDate checkOut = checkIn.plusDays(randomNumberOfNights);

        return new Guest(name, guestType, checkIn, checkOut);
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
