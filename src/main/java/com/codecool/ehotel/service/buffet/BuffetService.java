package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BuffetService {
    public void refill(Meal meal, int amount, Buffet buffet);

    public boolean consumeFreshest(Buffet buffet, MealType mealType);

    public int collectWaste(Buffet buffet, LocalDateTime date, boolean endOfCycles);
    public int amountToMake (Buffet buffet, MealType mealType);

}
