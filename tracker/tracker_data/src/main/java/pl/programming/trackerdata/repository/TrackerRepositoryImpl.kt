package pl.programming.trackerdata.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.programming.trackerdata.local.TrackerDao
import pl.programming.trackerdata.mapper.toTrackableFood
import pl.programming.trackerdata.mapper.toTrackedFood
import pl.programming.trackerdata.mapper.toTrackedFoodEntity
import pl.programming.trackerdata.remote.OpenFoodApi
import pl.programming.trackerdomain.model.TrackableFood
import pl.programming.trackerdomain.model.TrackedFood
import pl.programming.trackerdomain.repository.TrackerRepository
import java.time.LocalDate

class TrackerRepositoryImpl(
    private val dao: TrackerDao,
    private val api: OpenFoodApi,
) : TrackerRepository {

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int,
    ): Result<List<TrackableFood>> {
        return try {
            val searchDto = api.searchFood(
                query = query,
                page = page,
                pageSize = pageSize,
            )
            Result.success(
                searchDto.products
                    .filter {
                        val calculatedCalories = it.nutriments.carbohydrates100g * 4f +
                            it.nutriments.proteins100g * 4f +
                            it.nutriments.fat100g * 9f
                        val lowerBound = calculatedCalories * 0.99f
                        val upperBound = calculatedCalories * 1.01f
                        it.nutriments.energyKcal100g in (lowerBound..upperBound)
                    }
                    .mapNotNull {
                        it.toTrackableFood()
                    },
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodForDate(
            localDate.dayOfMonth,
            localDate.monthValue,
            localDate.year,
        ).map { entities ->
            entities.map { it.toTrackedFood() }
        }
    }
}
