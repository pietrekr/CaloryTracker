package pl.programming.trackerdomain.usecase

import pl.programming.trackerdomain.model.TrackableFood
import pl.programming.trackerdomain.repository.TrackerRepository

class SearchFood(
    private val repository: TrackerRepository,
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        pageSize: Int = 40,
    ): Result<List<TrackableFood>> {
        if (query.isBlank()) {
            val empty: List<TrackableFood> = emptyList()
            Result.success(empty)
        }
        return repository.searchFood(
            query.trim(),
            page,
            pageSize,
        )
    }
}
