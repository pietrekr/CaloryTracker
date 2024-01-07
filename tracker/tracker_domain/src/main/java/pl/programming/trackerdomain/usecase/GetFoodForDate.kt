package pl.programming.trackerdomain.usecase

import kotlinx.coroutines.flow.Flow
import pl.programming.trackerdomain.model.TrackedFood
import pl.programming.trackerdomain.repository.TrackerRepository
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
