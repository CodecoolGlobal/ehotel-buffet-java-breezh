package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
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
    public int collectWaste(Buffet buffet, LocalDate date) {
        int totalCost = 0;
        for (Meal meal : buffet.meals()) {
            if (expirationDate(meal).isAfter(date)) {
                totalCost += meal.mealType().getCost();
                buffet.meals().remove(meal);
            }
        }
        return totalCost;
    }

    private int expirationDays(MealDurability mealDurability) {
        return switch (mealDurability) {
            case SHORT -> 1;
            case MEDIUM -> 2;
            case LONG -> 3;
        };
    }

    private LocalDate expirationDate(Meal meal) {
        return meal.creationTime().plusDays(expirationDays(meal.mealType().getDurability()));
    }
}
