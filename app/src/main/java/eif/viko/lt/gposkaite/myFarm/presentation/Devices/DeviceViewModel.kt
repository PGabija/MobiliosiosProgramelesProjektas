package eif.viko.lt.gposkaite.myFarm.presentation.Devices

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eif.viko.lt.gposkaite.myFarm.data.Devices.Device
import eif.viko.lt.gposkaite.myFarm.data.Devices.DeviceDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DeviceViewModel(
    private val dao: DeviceDao
) : ViewModel() {

    private val isSortedByType = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private var devices =
        isSortedByType.flatMapLatest { sort ->
            if (sort) {
                dao.getDevicesOrderdByType()
            } else if (sort) {
                dao.getDevicesOrderdByName()
            }
            else {
                dao.getDevicesOrderdByAmount()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(DeviceState())
    val state =
        combine(_state, isSortedByType, devices) { state, isSortedByType, devices ->
            state.copy(
                devices = devices
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DeviceState())

    fun onEvent(event: DevicesEvent) {
        when (event) {
            is DevicesEvent.DeleteDevice -> {
                viewModelScope.launch {
                    dao.deleteDevice(event.device)
                }
            }

            is DevicesEvent.SaveDevice -> {
                val device = Device(
                    type = state.value.type.value,
                    name = state.value.name.value,
                    amount = state.value.amount.value
                )

                viewModelScope.launch {
                    dao.upsertDevice(device)
                }

                _state.update {
                    it.copy(
                        type = mutableStateOf(""),
                        name = mutableStateOf(""),
                        amount = mutableStateOf("")
                    )
                }
            }

            DevicesEvent.SortDevices -> {
                isSortedByType.value = !isSortedByType.value
            }

            else -> {}
        }
    }
}