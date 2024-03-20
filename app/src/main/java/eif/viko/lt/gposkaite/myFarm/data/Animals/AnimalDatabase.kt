package eif.viko.lt.gposkaite.myFarm.data.Animals

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Animal::class],
    version = 1
)
abstract class AnimalDatabase: RoomDatabase(){
    abstract val dao: AnimalDao
}