package eif.viko.lt.gposkaite.myFarm.presentation.Animals

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eif.viko.lt.gposkaite.myFarm.data.Animals.Animal
import eif.viko.lt.gposkaite.myFarm.data.Animals.AnimalDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val dao: AnimalDao
) : ViewModel() {

    private val isSortedByType = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private var animals =
        isSortedByType.flatMapLatest { sort ->
            if (sort) {
                dao.getAnimalsOrderdByType()
            }
            else {
            dao.getAnimalsOrderdByAmount()
        }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(AnimalState())
    val state =
        combine(_state, isSortedByType, animals) { state, isSortedByType, animals ->
            state.copy(
                animals = animals
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AnimalState())

    fun onEvent(event: AnimalsEvent) {
        when (event) {
            is AnimalsEvent.DeleteAnimal -> {
                viewModelScope.launch {
                    dao.deleteAnimal(event.animal)
                }
            }
            is AnimalsEvent.SaveAnimal -> {
                val animal = Animal(
                    type = state.value.type.value,
                    amount = state.value.amount.value
                )
                viewModelScope.launch {
                    dao.upsertAnimal(animal)
                }
                _state.update {
                    it.copy(
                        type = mutableStateOf(""),
                        amount = mutableStateOf("")
                    )
                }
            }
            AnimalsEvent.SortAnimals -> {
                isSortedByType.value = !isSortedByType.value
            }
        }
    }
}