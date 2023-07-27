package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BuffetServiceImpl implements BuffetService {
    Buffet buffet;

    public BuffetServiceImpl(Buffet buffet) {
        this.buffet = buffet;
    }

    @Override
    public void refill(Meal meal, int amount, Buffet buffet) {
        for (int i = 0; i <= amount; i++) {
            buffet.meals().add(meal);
        }
    }

    @Override
    public boolean consumeFreshest(Buffet buffet, MealType mealType) {
        for (Meal meal : buffet.meals()) {
            if (meal.mealType().equals(mealType)) {
                buffet.mostFresh(buffet.meals(), mealType);
                buffet.meals().remove(0);
                return true;
            }
        }
        return false;
    }

    @Override
    public int collectWaste(Buffet buffet, LocalDateTime date, boolean endOfCycles) {
        int totalCost = 0;
        for (Meal meal : buffet.meals()) {
            if (meal.mealType().getDurability() == MealDurability.SHORT) {
                if (date.isAfter(meal.creationTime().plusMinutes(90))) {
                    totalCost += meal.mealType().getCost();
                    buffet.meals().remove(meal);
                }
            }
            if (endOfCycles) {
                if (meal.mealType().getDurability() != MealDurability.LONG) {
                    totalCost += meal.mealType().getCost();
                    buffet.meals().remove(meal);
                }
            }
        }
        return totalCost;
    }


}
