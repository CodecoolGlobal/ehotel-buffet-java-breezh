package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.util.List;

public class BuffetServiceImpl implements BuffetService{
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
}
