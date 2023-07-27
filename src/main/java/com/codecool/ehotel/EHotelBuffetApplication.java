package com.codecool.ehotel;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.buffet.BuffetServiceImpl;
import com.codecool.ehotel.service.guest.GuestService;
import com.codecool.ehotel.service.guest.GuestServiceImpl;
import com.codecool.ehotel.service.logic.*;

import java.util.ArrayList;
import java.util.List;

public class EHotelBuffetApplication {

    BuffetService buffetService;
    GuestService guestService;
    GuestGenerator guestGenerator;
    GuestGroupBuilder guestGroupBuilder;
    Buffet buffet;
    int wasteCost = 0;
    int unsatisfiedGuest = 0;

    public static void main(String[] args) {
        List<Meal> meals = new ArrayList<>();
        Buffet buffet = new Buffet(meals);
        BuffetService buffetService = new BuffetServiceImpl(buffet);
        GuestService guestService = new GuestServiceImpl();
        GuestGenerator guestGenerator = new GuestGeneratorImpl(guestService);
        GuestGroupBuilder guestGroupBuilder = new GuestGroupBuilderImpl();

        BreakfastManager breakfastManager = new BreakfastManager(buffetService, guestService, guestGenerator, guestGroupBuilder, buffet);

        breakfastManager.serve();
    }
}
