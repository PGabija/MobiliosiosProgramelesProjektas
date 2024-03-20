package eif.viko.lt.gposkaite.myFarm.presentation.Devices

import eif.viko.lt.gposkaite.myFarm.data.Devices.Device

sealed interface DevicesEvent {
    object SortDevices: DevicesEvent
    data class DeleteDevice(val device: Device): DevicesEvent
    data class SaveDevice(
        val type: String,
        val name: String,
        val amount: String
    ): DevicesEvent
}