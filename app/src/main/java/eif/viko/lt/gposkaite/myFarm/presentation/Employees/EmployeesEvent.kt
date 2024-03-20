package eif.viko.lt.gposkaite.myFarm.presentation.Employees

import eif.viko.lt.gposkaite.myFarm.data.Employees.Employee

sealed interface EmployeesEvent {
    object SortEmployees: EmployeesEvent

    data class DeleteEmployee(val employee: Employee): EmployeesEvent

    data class SaveEmployee(
        val firstName: String,
        val lastName: String,
        val position: String,
        val phoneNumber: String,
        val email: String
    ): EmployeesEvent
}