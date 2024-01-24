package pl.programming.trackerdomain.usecase

import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import pl.programming.core.domain.model.ActivityLevel
import pl.programming.core.domain.model.Gender
import pl.programming.core.domain.model.GoalType
import pl.programming.core.domain.model.UserInfo
import pl.programming.core.domain.preferences.Preferences
import pl.programming.trackerdomain.model.MealType
import pl.programming.trackerdomain.model.TrackedFood
import java.time.LocalDate
import kotlin.random.Random

class CalculateMealNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setUp() {
        val preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f,
        )
        calculateMealNutrients = CalculateMealNutrients(preferences)
    }

    @Test
    fun `Calories for breakfast properly calculated`() {
        val trackedFood = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random(),
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000),
            )
        }

        val result = calculateMealNutrients(trackedFood)

        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }

        val expectedCalories = trackedFood
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Carbs for dinner properly calculated`() {
        val trackedFood = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random(),
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000),
            )
        }

        val result = calculateMealNutrients(trackedFood)

        val dinnerCarbs = result.mealNutrients.values
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }

        val exptectedCarbs = trackedFood
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.carbs }

        assertThat(dinnerCarbs).isEqualTo(exptectedCarbs)
    }
}
