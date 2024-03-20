package eif.viko.lt.gposkaite.myFarm.data.Adds

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import eif.viko.lt.gposkaite.myFarm.data.dataConverter.PainterConverter

@Database(
    entities = [Advertisement::class],
    version = 1
)
@TypeConverters(PainterConverter::class)
abstract class AdvertisementDatabase: RoomDatabase(){
    abstract fun AdvertisementDao(): AdvertisementDao
}