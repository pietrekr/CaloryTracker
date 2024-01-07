package pl.programming.trackerdata.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pl.programming.trackerdata.local.entity.TrackedFoodEntity

@Dao
interface TrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackedFoodEntity: TrackedFoodEntity)

    @Query(
        """
            SELECT * FROM trackedfoodentity
            WHERE dayOfMonth = :day AND month = :month AND year = :year
        """,
    )
    fun getFoodForDate(day: Int, month: Int, year: Int): Flow<List<TrackedFoodEntity>>
}
