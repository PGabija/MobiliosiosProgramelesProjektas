package eif.viko.lt.gposkaite.myFarm.data.dataConverter

import androidx.compose.ui.graphics.painter.Painter
import androidx.room.TypeConverter
import com.google.gson.Gson

class PainterConverter {
    @TypeConverter
    fun fromPainter(painter: Painter): String {
        return Gson().toJson(painter)
    }

    @TypeConverter
    fun toPainter(painterString: String): Painter {
        return Gson().fromJson(painterString, Painter::class.java)
    }
}
