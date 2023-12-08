package pl.programming.tracker_domain.repository

import kotlinx.coroutines.flow.Flow
import pl.programming.tracker_domain.model.TrackableFood
import pl.programming.tracker_domain.model.TrackedFood
import java.time.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int,
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}
