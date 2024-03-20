package eif.viko.lt.gposkaite.myFarm.util

import android.content.Context
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import eif.viko.lt.gposkaite.myFarm.presentation.Animals.AnimalsActivity
import eif.viko.lt.gposkaite.myFarm.presentation.Devices.DevicesActivity
import eif.viko.lt.gposkaite.myFarm.presentation.Employees.EmployeesActivity
import eif.viko.lt.gposkaite.myFarm.LoginActivity
import eif.viko.lt.gposkaite.myFarm.presentation.Main.MainActivity

object NavigationUtils {
    fun navigateToHome(context: Context) {
        context.startActivity(Intent(context, MainActivity::class.java))
    }

    fun navigateToAnimals(context: Context) {
        context.startActivity(Intent(context, AnimalsActivity::class.java))
    }

    fun navigateToDevices(context: Context) {
        context.startActivity(Intent(context, DevicesActivity::class.java))
    }

    fun navigateToEmployees(context: Context) {
        context.startActivity(Intent(context, EmployeesActivity::class.java))
    }

    fun signOut(context: Context) {
        FirebaseAuth.getInstance().signOut()
        context.startActivity(Intent(context, LoginActivity::class.java))
    }
}