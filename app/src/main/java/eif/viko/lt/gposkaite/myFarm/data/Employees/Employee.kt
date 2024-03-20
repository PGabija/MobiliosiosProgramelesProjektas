package eif.viko.lt.gposkaite.myFarm.data.Employees

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee(
    val firstName: String,
    val lastName: String,
    val position: String,
    val phoneNumber: String,
    val email: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)