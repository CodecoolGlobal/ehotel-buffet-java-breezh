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
    int totalGuests = 0;

    public BreakfastManager(BuffetService buffetService, GuestService guestService, GuestGenerator guestGenerator, GuestGroupBuilder guestGroupBuilder, Buffet buffet) {
        this.buffetService = buffetService;
        this.guestService = guestService;
        this.guestGenerator = guestGenerator;
        this.guestGroupBuilder = guestGroupBuilder;
        this.buffet = buffet;
    }

    public void serve() {
        Random random = new Random();

        Set<Guest> fullGuestList = guestGenerator.generateGuests(guestService, 500, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-02-01"));
        Set<Guest> guestsForTheDay = guestService.getGuestsForDay(fullGuestList, LocalDate.parse("2023-01-15"));
        ArrayList<HashSet<Guest>> guestCycles = guestGroupBuilder.generateGroups(guestsForTheDay);

        List<Meal> meals = new ArrayList<>();
        Buffet buffet = new Buffet(meals);

        LocalDateTime initialTime = LocalDateTime.parse("2023-01-15T06:00:00");


        for (int i = 0; i < 8; i++) {


            buffetService.refill((new Meal(MealType.SCRAMBLED_EGGS, initialTime)), buffetService.amountToMake(buffet, MealType.SCRAMBLED_EGGS), buffet);
            buffetService.refill((new Meal(MealType.SUNNY_SIDE_UP, initialTime)), buffetService.amountToMake(buffet, MealType.SUNNY_SIDE_UP), buffet);
            buffetService.refill((new Meal(MealType.FRIED_SAUSAGE, initialTime)), buffetService.amountToMake(buffet, MealType.FRIED_SAUSAGE), buffet);
            buffetService.refill((new Meal(MealType.FRIED_BACON, initialTime)), buffetService.amountToMake(buffet, MealType.FRIED_BACON), buffet);
            buffetService.refill((new Meal(MealType.PANCAKE, initialTime)), buffetService.amountToMake(buffet, MealType.PANCAKE), buffet);
            buffetService.refill((new Meal(MealType.CROISSANT, initialTime)), buffetService.amountToMake(buffet, MealType.CROISSANT), buffet);
            buffetService.refill((new Meal(MealType.MASHED_POTATO, initialTime)), buffetService.amountToMake(buffet, MealType.MASHED_POTATO), buffet);
            buffetService.refill((new Meal(MealType.MUFFIN, initialTime)), buffetService.amountToMake(buffet, MealType.MUFFIN), buffet);
            buffetService.refill((new Meal(MealType.BUN, initialTime)), buffetService.amountToMake(buffet, MealType.BUN), buffet);
            buffetService.refill((new Meal(MealType.CEREAL, initialTime)), buffetService.amountToMake(buffet, MealType.CEREAL), buffet);
            buffetService.refill((new Meal(MealType.MILK, initialTime)), buffetService.amountToMake(buffet, MealType.MILK), buffet);


            for (Guest guest : guestCycles.get(i)) {
                MealType favoredMeal = guest.guestType().getMealPreferences().get(random.nextInt(guest.guestType().getMealPreferences().size()));
                if (!buffetService.consumeFreshest(buffet, favoredMeal)) {
                    unsatisfiedGuest++;
                }
                totalGuests++;
            }

            if (i == 7) {
                wasteCost += buffetService.collectWaste(buffet, initialTime.plusMinutes(30), true);
            } else {
                wasteCost += buffetService.collectWaste(buffet, initialTime.plusMinutes(30), false);
            }

            initialTime = initialTime.plusMinutes(30);
            System.out.println(i + 1 +". cycle " + initialTime.minusMinutes(30) + " " + initialTime);
            System.out.println("Waste cost: " + wasteCost);
            System.out.println("Unsatisfied guests: " +unsatisfiedGuest);
            System.out.println("-------------------");
        }
        System.out.println("-------------------------SUMMARY-------------------------");
        System.out.println("Waste cost: " + wasteCost);
        System.out.println("Unsatisfied guests: " + unsatisfiedGuest);
        System.out.println("Total guests for the day " + totalGuests);
        System.out.println( Math.round(100 - (double) unsatisfiedGuest/totalGuests * 100) + " % of the guests were satisfied.");
    }

}
