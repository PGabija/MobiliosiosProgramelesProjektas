package eif.viko.lt.gposkaite.myFarm

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import eif.viko.lt.gposkaite.R
import eif.viko.lt.gposkaite.myFarm.model.UserModel
import eif.viko.lt.gposkaite.myFarm.presentation.Main.MainActivity
import eif.viko.lt.gposkaite.myFarm.util.UiUtil

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupScreen()
        }
    }
}
@Composable
fun SignupScreen() {
    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

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
                    if (password != confirmPassword) {
                        UiUtil.showToast(context, "Slaptažodžiai nesutampa")
                        return@KeyboardActions
                    }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        email, password
                    ).addOnSuccessListener { result ->
                        val user = result.user
                        user?.let {
                            val userModel = UserModel(it.uid, email, email.substringBefore("@"))
                            Firebase.firestore.collection("users")
                                .document(it.uid)
                                .set(userModel).addOnSuccessListener {
                                    UiUtil.showToast(context, "Paskyra sėkmingai sukurta")
                                    context.startActivity(Intent(context, MainActivity::class.java))
                                }
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

        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(stringResource(id = R.string.confirm_password)) },
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
                    if (password != confirmPassword) {
                        UiUtil.showToast(context, "Slaptažodžiai nesutampa")
                        return@KeyboardActions
                    }

                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                        email, password
                    ).addOnSuccessListener { result ->
                        val user = result.user
                        user?.let {
                            val userModel = UserModel(it.uid, email, email.substringBefore("@"))
                            Firebase.firestore.collection("users")
                                .document(it.uid)
                                .set(userModel).addOnSuccessListener {
                                    UiUtil.showToast(context, "Paskyra sėkmingai sukurtą")
                                    context.startActivity(Intent(context, MainActivity::class.java))
                                }
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
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    UiUtil.showToast(context, "El. paštas neteisingas")
                    return@Button
                }

                if (password.length < 6) {
                    UiUtil.showToast(context, "Mažiausiai 6 simboliai")
                    return@Button
                }

                if (password != confirmPassword) {
                    UiUtil.showToast(context, "Slaptažodžiai nesutampa")
                    return@Button
                }

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    email, password
                ).addOnSuccessListener { result ->
                    val user = result.user
                    user?.let {
                        val userModel = UserModel(it.uid, email, email.substringBefore("@"))
                        Firebase.firestore.collection("users")
                            .document(it.uid)
                            .set(userModel).addOnSuccessListener {
                                UiUtil.showToast(context, "Paskyra sėkmingai sukurta")
                                context.startActivity(Intent(context, MainActivity::class.java))
                            }
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
            Text(stringResource(id = R.string.register))
        }

        Button(
            onClick = {
                context.startActivity(Intent(context, LoginActivity::class.java))
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = Color(0xFFFF9800))
        ) {
            Text(stringResource(id = R.string.have_account))
        }
    }
}