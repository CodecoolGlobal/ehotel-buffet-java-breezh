package com.codecool.ehotel.service.logic;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;
import com.codecool.ehotel.service.buffet.BuffetService;
import com.codecool.ehotel.service.guest.GuestService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class BreakfastManager {
    BuffetService buffetService;
    GuestService guestService;
    GuestGenerator guestGenerator;
    GuestGroupBuilder guestGroupBuilder;
    Buffet buffet;
    int wasteCost = 0;
    int unsatisfiedGuest = 0;

    public BreakfastManager(BuffetService buffetService, GuestService guestService, GuestGenerator guestGenerator, GuestGroupBuilder guestGroupBuilder, Buffet buffet) {
        this.buffetService = buffetService;
        this.guestService = guestService;
        this.guestGenerator = guestGenerator;
        this.guestGroupBuilder = guestGroupBuilder;
        this.buffet = buffet;
    }

    public int getWasteCost() {
        return wasteCost;
    }

    public int getUnsatisfiedGuest() {
        return unsatisfiedGuest;
    }

    public void serve() {
        Random random = new Random();

        Set<Guest> fullGuestList = guestGenerator.generateGuests(guestService, 100, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-02-01"));
        Set<Guest> guestsForTheDay = guestService.getGuestsForDay(fullGuestList, LocalDate.parse("2023-01-15"));
        ArrayList<HashSet<Guest>> guestCycles = guestGroupBuilder.generateGroups(guestsForTheDay);

        List<Meal> meals = new ArrayList<>();
        Buffet buffet = new Buffet(meals);

        LocalDateTime initialTime = LocalDateTime.parse("2023-01-15T06:00:00");

        //first cycle


        for (int i = 0; i < 8; i++) {


            buffetService.refill((new Meal(MealType.SCRAMBLED_EGGS, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.SUNNY_SIDE_UP, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.FRIED_SAUSAGE, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.FRIED_BACON, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.PANCAKE, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.CROISSANT, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.MASHED_POTATO, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.MUFFIN, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.BUN, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.CEREAL, initialTime)), 5, buffet);
            buffetService.refill((new Meal(MealType.MILK, initialTime)), 5, buffet);

            for (Guest guest : guestCycles.get(0)) {
                MealType favoredMeal = guest.guestType().getMealPreferences().get(random.nextInt(guest.guestType().getMealPreferences().size()));
                if (!buffetService.consumeFreshest(buffet, favoredMeal)) {
                    unsatisfiedGuest++;
                }
            }

            if (i == 7) {
                wasteCost += buffetService.collectWaste(buffet, initialTime.plusMinutes(30), true);
            } else {
                wasteCost += buffetService.collectWaste(buffet, initialTime.plusMinutes(30), false);
            }

            initialTime = initialTime.plusMinutes(30);
            System.out.println("cycle end");
            System.out.println(wasteCost);
            System.out.println(unsatisfiedGuest);
        }
        System.out.println("----");
        System.out.println(wasteCost);
        System.out.println(unsatisfiedGuest);
        System.out.println("breakfast end");
    }
}
