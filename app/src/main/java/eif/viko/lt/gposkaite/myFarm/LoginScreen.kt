package eif.viko.lt.gposkaite.myFarm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.FirebaseAuth
import eif.viko.lt.gposkaite.R
import eif.viko.lt.gposkaite.myFarm.model.LoginViewModel
import eif.viko.lt.gposkaite.myFarm.presentation.Main.MainActivity
import eif.viko.lt.gposkaite.myFarm.util.UiUtil

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()
        }
    }
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val viewModel: LoginViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xC4FF9800), Color(0xF389FF00))
                )
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .padding(12.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(stringResource(id = R.string.email))},
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = null)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(stringResource(id = R.string.password)) },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.MailOutline, contentDescription = null)
            },
            trailingIcon = {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {

                }
            },

            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    auth.signInWithEmailAndPassword(
                        email, password
                    ).addOnSuccessListener { result ->
                        val user = result.user
                        user?.let {
                            viewModel.onLoginSuccess(user.uid)
                            UiUtil.showToast(context, "Signin Successful")
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                    }.addOnFailureListener {
                        UiUtil.showToast(context, it.localizedMessage ?: "Something went wrong")
                    }
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )

        Button(
            onClick = {
                auth.signInWithEmailAndPassword(
                    email, password
                ).addOnSuccessListener { result ->
                    val user = result.user
                    user?.let {
                        viewModel.onLoginSuccess(user.uid)
                        UiUtil.showToast(context, "Prisijungimas sėkmingas")
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
                }.addOnFailureListener {
                    UiUtil.showToast(context, it.localizedMessage ?: "Something went wrong")
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color(0xFFFF9800))
        ) {
            Text(stringResource(id = R.string.login))
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, SignupActivity::class.java))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color(0xFFFF9800))
        ) {
            Text(stringResource(id = R.string.do_not_have_account_register))
        }
    }
}
