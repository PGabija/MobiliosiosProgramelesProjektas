package eif.viko.lt.gposkaite.myFarm.data.Employees

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Employee::class],
    version = 1
)
abstract class EmployeeDatabase: RoomDatabase(){
    abstract val dao: EmployeeDao
}