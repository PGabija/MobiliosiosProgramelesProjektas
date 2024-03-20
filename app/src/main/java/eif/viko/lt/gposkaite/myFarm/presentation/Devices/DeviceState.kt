package eif.viko.lt.gposkaite.myFarm.presentation.Devices

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import eif.viko.lt.gposkaite.myFarm.data.Devices.Device

data class DeviceState(
    val devices: List<Device> = emptyList(),
    val type: MutableState<String> = mutableStateOf(""),
    val name: MutableState<String> = mutableStateOf(""),
    val amount: MutableState<String> = mutableStateOf("")
)