package pl.programming.trackerdomain.usecase

import pl.programming.trackerdomain.model.TrackedFood
import pl.programming.trackerdomain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository,
) {
    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}
