package eif.viko.lt.gposkaite.myFarm.data.Devices

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Device(
    val type: String,
    val name: String,
    val amount: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)