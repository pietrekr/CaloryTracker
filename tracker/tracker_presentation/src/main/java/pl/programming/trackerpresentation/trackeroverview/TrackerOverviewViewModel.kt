package pl.programming.trackerpresentation.trackeroverview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import pl.programming.core.domain.preferences.Preferences
import pl.programming.core.utils.UiEvent
import pl.programming.trackerdomain.usecase.TrackerUseCases
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases,
) : ViewModel() {

    var state by mutableStateOf(TrackerOverviewState())
        private set
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodForDateJob: Job? = null

    init {
        refreshFood()
        preferences.saveShouldShowOnboarding(false)
    }

    fun onEvent(event: TrackerOverviewEvent) {
        when (event) {
            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(event.trackedFood)
                    refreshFood()
                }
            }

            is TrackerOverviewEvent.OnNextDayClick -> {
                state = state.copy(
                    date = state.date.plusDays(1),
                )
                refreshFood()
            }

            is TrackerOverviewEvent.OnPreviousDayClick -> {
                state = state.copy(
                    date = state.date.minusDays(1),
                )
                refreshFood()
            }

            is TrackerOverviewEvent.OnToggleMealClick -> {
                state = state.copy(
                    meals = state.meals.map {
                        if (it.name == event.meal.name) {
                            it.copy(isExpended = !it.isExpended)
                        } else {
                            it
                        }
                    },
                )
            }
        }
    }

    private fun refreshFood() {
        getFoodForDateJob?.cancel()
        getFoodForDateJob = trackerUseCases
            .getFoodForDate(state.date)
            .onEach { food ->
                val nutrientsResult = trackerUseCases.calculateMealNutrients(food)
                state = state.copy(
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalProtein = nutrientsResult.totalProtein,
                    totalFat = nutrientsResult.totalFat,
                    totalCalories = nutrientsResult.totalCalories,
                    carbsGoal = nutrientsResult.carbsGoal,
                    proteinGoal = nutrientsResult.proteinGoal,
                    fatGoal = nutrientsResult.fatGoal,
                    caloriesGoal = nutrientsResult.caloriesGoal,
                    trackedFood = food,
                    meals = state.meals.map {
                        val nutrientsForMeal = nutrientsResult.mealNutrients[it.mealType]
                            ?: return@map it.copy(
                                carbs = 0,
                                protein = 0,
                                fat = 0,
                                calories = 0,
                            )
                        it.copy(
                            carbs = nutrientsForMeal.carbs,
                            protein = nutrientsForMeal.protein,
                            fat = nutrientsForMeal.fat,
                            calories = nutrientsForMeal.calories,
                        )
                    },
                )
            }.launchIn(viewModelScope)
    }
}
