package pl.programming.trackerdata.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.programming.trackerdata.local.entity.TrackedFoodEntity

@Database(
    entities = [TrackedFoodEntity::class],
    version = 1,
)
abstract class TrackerDatabase : RoomDatabase() {
    abstract val dao: TrackerDao
}
