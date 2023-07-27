package com.codecool.ehotel.model;

import java.time.LocalDateTime;

public record Meal(MealType mealType, LocalDateTime creationTime) {
}
