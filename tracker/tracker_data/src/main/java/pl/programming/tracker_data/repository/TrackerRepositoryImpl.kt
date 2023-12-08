package pl.programming.tracker_data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.programming.tracker_data.local.TrackerDao
import pl.programming.tracker_data.mapper.toTrackableFood
import pl.programming.tracker_data.mapper.toTrackedFood
import pl.programming.tracker_data.mapper.toTrackedFoodEntity
import pl.programming.tracker_data.remote.OpenFoodApi
import pl.programming.tracker_domain.model.TrackableFood
import pl.programming.tracker_domain.model.TrackedFood
import pl.programming.tracker_domain.repository.TrackerRepository
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
                searchDto.products.mapNotNull {
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
