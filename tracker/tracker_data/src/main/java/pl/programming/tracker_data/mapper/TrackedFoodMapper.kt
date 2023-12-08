package pl.programming.tracker_data.mapper

import pl.programming.tracker_data.local.entity.TrackedFoodEntity
import pl.programming.tracker_domain.model.MealType
import pl.programming.tracker_domain.model.TrackedFood
import java.time.LocalDate

fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = this.name,
        carbs = this.carbs,
        protein = this.protein,
        fat = this.fat,
        imageUrl = this.imageUrl,
        mealType = MealType.fromString(this.type),
        amount = this.amount,
        date = LocalDate.of(this.year, this.month, this.dayOfMonth),
        calories = this.calories,
        id = this.id
    )
}

fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}