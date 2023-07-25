package com.codecool.ehotel.model;

import java.time.LocalDate;

public record Meal(MealType mealType, LocalDate creationTime) {
}
