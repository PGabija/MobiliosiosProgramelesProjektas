package eif.viko.lt.gposkaite.myFarm.presentation.Employees

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eif.viko.lt.gposkaite.myFarm.data.Employees.Employee
import eif.viko.lt.gposkaite.myFarm.data.Employees.EmployeeDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EmployeeViewModel(
    private val dao: EmployeeDao
) : ViewModel() {

    private val isSortedByName = MutableStateFlow(true)

    @OptIn(ExperimentalCoroutinesApi::class)
    private var employees =
        isSortedByName.flatMapLatest { sort ->
            if (sort) {
                dao.getEmployeesOrderdByName()
            }
            else if (sort) {
                dao.getEmployeesOrderdByLastName()
            }
            else if (sort) {
                dao.getEmployeesOrderdByPosition()
            }
            else if (sort) {
                dao.getEmployeesOrderdByPhoneNumber()
            }
            else {
                dao.getEmployeesOrderdByEmail()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(EmployeeState())
    val state =
        combine(_state, isSortedByName, employees) { state, isSortedByName, employees ->
            state.copy(
                employees = employees
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EmployeeState())

    fun onEvent(event: EmployeesEvent) {
        when (event) {
            is EmployeesEvent.DeleteEmployee -> {
                viewModelScope.launch {
                    dao.deleteEmployee(event.employee)
                }
            }

            is EmployeesEvent.SaveEmployee -> {
                val employee = Employee(
                    firstName = state.value.firstName.value,
                    lastName = state.value.lastName.value,
                    position = state.value.position.value,
                    phoneNumber = state.value.phoneNumber.value,
                    email = state.value.email.value
                )

                viewModelScope.launch {
                    dao.upsertEmployee(employee)
                }

                _state.update {
                    it.copy(
                        firstName = mutableStateOf(""),
                        lastName = mutableStateOf(""),
                        position = mutableStateOf(""),
                        phoneNumber = mutableStateOf(""),
                        email = mutableStateOf("")
                    )
                }
            }
            EmployeesEvent.SortEmployees -> {
                isSortedByName.value = !isSortedByName.value
            }
        }
    }
}