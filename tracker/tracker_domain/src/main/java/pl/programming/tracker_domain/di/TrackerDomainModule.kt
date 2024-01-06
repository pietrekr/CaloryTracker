package pl.programming.tracker_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.programming.core.domain.preferences.Preferences
import pl.programming.tracker_domain.repository.TrackerRepository
import pl.programming.tracker_domain.usecase.CalculateMealNutrients
import pl.programming.tracker_domain.usecase.DeleteTrackedFood
import pl.programming.tracker_domain.usecase.GetFoodForDate
import pl.programming.tracker_domain.usecase.SearchFood
import pl.programming.tracker_domain.usecase.TrackFood
import pl.programming.tracker_domain.usecase.TrackerUseCases

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences,
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            getFoodForDate = GetFoodForDate(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences),
        )
    }
}
