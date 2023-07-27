package com.codecool.ehotel.model;


import java.util.Comparator;
import java.util.List;


public record Buffet (List<Meal> meals) {

    public void mostFresh(List<Meal> meals, MealType mealType) {
         meals.stream().filter(meal -> meal.mealType() == mealType).sorted(Comparator.comparing(Meal::creationTime).reversed()).toList();
    }

    public void leastFresh(List<Meal> meals, MealType mealType) {
         meals.stream().filter(meal -> meal.mealType() == mealType).sorted(Comparator.comparing(Meal::creationTime)).toList();
    }

}
