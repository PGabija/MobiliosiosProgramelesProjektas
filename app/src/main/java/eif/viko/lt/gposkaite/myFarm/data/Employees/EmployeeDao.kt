package eif.viko.lt.gposkaite.myFarm.data.Employees

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Upsert
    suspend fun upsertEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Query("SELECT * FROM employee ORDER BY firstName")
    fun getEmployeesOrderdByName(): Flow<List<Employee>>

    @Query("SELECT * FROM employee ORDER BY lastName ASC")
    fun getEmployeesOrderdByLastName(): Flow<List<Employee>>

    @Query("SELECT * FROM employee ORDER BY position ASC")
    fun getEmployeesOrderdByPosition(): Flow<List<Employee>>

    @Query("SELECT * FROM employee ORDER BY phoneNumber ASC")
    fun getEmployeesOrderdByPhoneNumber(): Flow<List<Employee>>

    @Query("SELECT * FROM employee ORDER BY email ASC")
    fun getEmployeesOrderdByEmail(): Flow<List<Employee>>
}