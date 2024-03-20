package eif.viko.lt.gposkaite.myFarm.presentation.Devices

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
fun AddDeviceScreen(
    state: DeviceState,
    navController: NavController,
    onEvent: (DevicesEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(
                    DevicesEvent.SaveDevice(
                        type = state.type.value,
                        name = state.name.value,
                        amount = state.amount.value
                    )
                )
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = stringResource(id = R.string.save_device)
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
                value = state.type.value,
                onValueChange = {
                    state.type.value = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(stringResource(id = R.string.type))
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.name.value,
                onValueChange = {
                    state.name.value = it
                },
                placeholder = {
                    Text(stringResource(id = R.string.name))
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.amount.value,
                onValueChange = {
                    state.amount.value = it
                },
                placeholder = {
                    Text(stringResource(id = R.string.amount))
                }
            )
        }
    }
}