package eif.viko.lt.gposkaite.myFarm.data.Adds

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AdvertisementDao {

    @Upsert
    suspend fun upsertAdvertisement(advertisement: Advertisement)

    @Delete
    suspend fun deleteAdvertisement(advertisement: Advertisement)

    @Query("SELECT * FROM advertisement ORDER BY title")
    fun getAdvertisementOrderdByTitle(): Flow<List<Advertisement>>

    @Query("SELECT * FROM advertisement ORDER BY rating ASC")
    fun getAdvertisementOrderdByRating(): Flow<List<Advertisement>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdvertisement(advertisement: Advertisement)

    @Query("SELECT * FROM Advertisement")
    suspend fun getAllAdvertisements(): List<Advertisement>
}