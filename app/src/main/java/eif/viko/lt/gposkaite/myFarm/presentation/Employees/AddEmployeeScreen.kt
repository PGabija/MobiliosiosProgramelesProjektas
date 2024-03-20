package eif.viko.lt.gposkaite.myFarm.presentation.Employees

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eif.viko.lt.gposkaite.R

@Composable
fun AddEmployeeScreen(
    state: EmployeeState,
    navController: NavController,
    onEvent: (EmployeesEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(
                    EmployeesEvent.SaveEmployee(
                        firstName = state.firstName.value,
                        lastName = state.lastName.value,
                        position = state.position.value,
                        phoneNumber = state.phoneNumber.value,
                        email = state.email.value
                    )
                )
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = stringResource(id = R.string.save_employee)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.firstName.value,
                onValueChange = {
                    state.firstName.value = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(stringResource(id = R.string.first_name))
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.lastName.value,
                onValueChange = {
                    state.lastName.value = it
                },
                placeholder = {
                    Text(stringResource(id = R.string.last_name))
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.position.value,
                onValueChange = {
                    state.position.value = it
                },
                placeholder = {
                    Text(stringResource(id = R.string.position))
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.phoneNumber.value,
                onValueChange = {
                    state.phoneNumber.value = it
                },
                placeholder = {
                    Text(stringResource(id = R.string.phone_number))
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.email.value,
                onValueChange = {
                    state.email.value = it
                },
                placeholder = {
                    Text(stringResource(id = R.string.email))
                }
            )
        }
    }
}