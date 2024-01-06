package pl.programming.tracker_domain.usecase

import kotlinx.coroutines.flow.Flow
import pl.programming.tracker_domain.model.TrackedFood
import pl.programming.tracker_domain.repository.TrackerRepository
import java.time.LocalDate

class GetFoodForDate(
    private val repository: TrackerRepository,
) {
    operator fun invoke(
        date: LocalDate,
    ): Flow<List<TrackedFood>> {
        return repository.getFoodForDate(date)
    }
}
