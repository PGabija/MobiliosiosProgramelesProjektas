package eif.viko.lt.gposkaite.myFarm.presentation.Devices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import eif.viko.lt.gposkaite.R

@Composable
fun DevicesScreen(
    state: DeviceState,
    navController: NavController,
    onEvent: (DevicesEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.type.value = ""
                state.name.value = ""
                state.amount.value = ""
                navController.navigate("AddDeviceScreen")
            },
                containerColor = Color(0xD8750026),) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = stringResource(id = R.string.add_new_device))
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(Color(0xFFFFEBF2)),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.devices.size) { index ->
                DeviceItem(
                    state = state,
                    index = index,
                    onEvent = onEvent
                )
            }
        }
    }
}

@Composable
fun DeviceItem(
    state: DeviceState,
    index: Int,
    onEvent: (DevicesEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0x5BFF0052))
            .padding(12.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = state.devices[index].type,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.devices[index].name,
                fontSize = 16.sp,
                color = Color(0xD8750026)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.devices[index].amount,
                fontSize = 16.sp,
                color = Color(0xD8750026)
            )
        }
        IconButton(
            onClick = {
                onEvent(DevicesEvent.DeleteDevice(state.devices[index]))
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = stringResource(id = R.string.delete_device),
                modifier = Modifier.size(35.dp),
                tint = Color(0xD8750026)
            )
        }
    }
}