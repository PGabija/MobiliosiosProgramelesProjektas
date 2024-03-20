package eif.viko.lt.gposkaite.myFarm.data.Animals

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {
    @Upsert
    suspend fun upsertAnimal(animal: Animal)

    @Delete
    suspend fun deleteAnimal(animal: Animal)

    @Query("SELECT * FROM animal ORDER BY type")
    fun getAnimalsOrderdByType(): Flow<List<Animal>>

    @Query("SELECT * FROM animal ORDER BY amount ASC")
    fun getAnimalsOrderdByAmount(): Flow<List<Animal>>
}