package eif.viko.lt.gposkaite.myFarm.data.Devices

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    @Upsert
    suspend fun upsertDevice(device: Device)

    @Delete
    suspend fun deleteDevice(device: Device)

    @Query("SELECT * FROM device ORDER BY type")
    fun getDevicesOrderdByType(): Flow<List<Device>>


    @Query("SELECT * FROM device ORDER BY name ASC")
    fun getDevicesOrderdByName(): Flow<List<Device>>

    @Query("SELECT * FROM device ORDER BY amount ASC")
    fun getDevicesOrderdByAmount(): Flow<List<Device>>
}