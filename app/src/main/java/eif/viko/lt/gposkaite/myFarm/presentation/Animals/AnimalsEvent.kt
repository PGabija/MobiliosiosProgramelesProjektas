package eif.viko.lt.gposkaite.myFarm.presentation.Animals

import eif.viko.lt.gposkaite.myFarm.data.Animals.Animal

sealed interface AnimalsEvent {
    object SortAnimals: AnimalsEvent

    data class DeleteAnimal(val animal: Animal): AnimalsEvent
    data class SaveAnimal(
        val type: String,
        val amount: String
    ): AnimalsEvent
}
