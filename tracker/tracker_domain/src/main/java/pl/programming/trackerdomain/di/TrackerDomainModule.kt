package pl.programming.trackerdomain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pl.programming.core.domain.preferences.Preferences
import pl.programming.trackerdomain.repository.TrackerRepository
import pl.programming.trackerdomain.usecase.CalculateMealNutrients
import pl.programming.trackerdomain.usecase.DeleteTrackedFood
import pl.programming.trackerdomain.usecase.GetFoodForDate
import pl.programming.trackerdomain.usecase.SearchFood
import pl.programming.trackerdomain.usecase.TrackFood
import pl.programming.trackerdomain.usecase.TrackerUseCases

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
