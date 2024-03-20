package eif.viko.lt.gposkaite.myFarm.presentation.Animals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import eif.viko.lt.gposkaite.myFarm.data.Animals.Animal

data class AnimalState(
    val animals: List<Animal> = emptyList(),
    val type: MutableState<String> = mutableStateOf(""),
    val amount: MutableState<String> = mutableStateOf("")
)