package eif.viko.lt.gposkaite.myFarm.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import androidx.room.Room
import eif.viko.lt.gposkaite.R
import eif.viko.lt.gposkaite.myFarm.data.Animals.Animal
import eif.viko.lt.gposkaite.myFarm.data.Animals.AnimalDatabase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AnimalListWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    companion object {
        internal fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val views = RemoteViews(context.packageName, R.layout.animal_list_widget)

            getAnimalsFromDatabaseAsync(context) { animals ->
                val animalListText = buildAnimalListText(animals)

                views.setTextViewText(R.id.appwidget_text, animalListText)

                appWidgetManager.updateAppWidget(appWidgetId, views)
            }
        }

        @OptIn(DelicateCoroutinesApi::class)
        private fun getAnimalsFromDatabaseAsync(
            context: Context,
            callback: (List<Animal>) -> Unit
        ) {
            val database = Room.databaseBuilder(
                context.applicationContext,
                AnimalDatabase::class.java, "animals.db"
            ).build()

            val dao = database.dao
            GlobalScope.launch {
                val animalsFlow = dao.getAnimalsOrderdByType()
                val animalsList = animalsFlow.first()
                callback(animalsList)
            }
        }

        private fun buildAnimalListText(animals: List<Animal>): String {
            val stringBuilder = StringBuilder()
            for (animal in animals) {
                stringBuilder.append("${animal.type}: ${animal.amount}\n")
            }
            return stringBuilder.toString()
        }
    }
}
