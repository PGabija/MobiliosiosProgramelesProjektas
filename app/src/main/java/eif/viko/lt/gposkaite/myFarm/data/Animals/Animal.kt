package eif.viko.lt.gposkaite.myFarm.data.Animals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Animal(
    val type: String,
    val amount: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)