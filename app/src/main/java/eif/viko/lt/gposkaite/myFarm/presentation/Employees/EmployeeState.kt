package eif.viko.lt.gposkaite.myFarm.presentation.Employees

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import eif.viko.lt.gposkaite.myFarm.data.Employees.Employee

data class EmployeeState(
    val employees: List<Employee> = emptyList(),
    val firstName: MutableState<String> = mutableStateOf(""),
    val lastName: MutableState<String> = mutableStateOf(""),
    val position: MutableState<String> = mutableStateOf(""),
    val phoneNumber: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String> = mutableStateOf("")
)