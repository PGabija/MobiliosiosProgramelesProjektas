package eif.viko.lt.gposkaite.myFarm.data.Devices

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Device::class],
    version = 1
)

abstract class DeviceDatabase: RoomDatabase(){
    abstract val dao: DeviceDao
}