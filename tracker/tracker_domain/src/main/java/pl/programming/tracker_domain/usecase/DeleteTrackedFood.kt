package pl.programming.tracker_domain.usecase

import kotlinx.coroutines.flow.Flow
import pl.programming.tracker_domain.model.TrackedFood
import pl.programming.tracker_domain.repository.TrackerRepository
import java.time.LocalDate

class DeleteTrackedFood(
    private val repository: TrackerRepository,
) {
    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}
