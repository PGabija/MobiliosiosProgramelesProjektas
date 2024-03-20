package eif.viko.lt.gposkaite.myFarm.data.Adds

import androidx.compose.ui.graphics.painter.Painter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import eif.viko.lt.gposkaite.myFarm.data.dataConverter.PainterConverter

@Entity
data class Advertisement(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") @TypeConverters(PainterConverter::class) val image: Painter,
    @ColumnInfo(name = "rating") val rating: Int,
    @ColumnInfo(name = "location") val location: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0
)
