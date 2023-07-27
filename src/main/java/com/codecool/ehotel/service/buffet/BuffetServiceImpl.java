package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;
import java.time.LocalDateTime;
import java.util.Iterator;

public class BuffetServiceImpl implements BuffetService {
    Buffet buffet;

    public BuffetServiceImpl(Buffet buffet) {
        this.buffet = buffet;
    }

    @Override
    public void refill(Meal meal, int amount, Buffet buffet) {
        for (int i = 0; i < amount; i++) {
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
        Iterator<Meal> mealIterator = buffet.meals().iterator();
        while (mealIterator.hasNext()) {
            Meal meal = mealIterator.next();
            if (meal.mealType().getDurability() == MealDurability.SHORT && date.isAfter(meal.creationTime().plusMinutes(90))) {
                totalCost += meal.mealType().getCost();
                mealIterator.remove();
            } else if (endOfCycles && meal.mealType().getDurability() != MealDurability.LONG) {
                totalCost += meal.mealType().getCost();
                mealIterator.remove();
            }
        }
        return totalCost;
    }

    public int amountToMake (Buffet buffet, MealType mealType) {
        int amountOStock = 0;
        int amountToMake;
        int amountMax = 0;
        switch (mealType.getDurability()) {
            case SHORT -> amountMax = 10;
            case MEDIUM -> amountMax = 15;
            case LONG -> amountMax = 30;
        }
        for(Meal meal : buffet.meals()) {
            if (meal.mealType().equals(mealType)){
                amountOStock++;
            }
        }
        amountToMake = Math.max(amountMax - amountOStock, 0);
        return amountToMake;
    }


}
