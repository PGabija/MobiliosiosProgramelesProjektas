package eif.viko.lt.gposkaite.myFarm.presentation.Animals

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
import androidx.compose.material3.MaterialTheme
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
fun AnimalsScreen(
    state: AnimalState,
    navController: NavController,
    onEvent: (AnimalsEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.type.value = ""
                state.amount.value = ""
                navController.navigate("AddAnimalScreen")
            },
                containerColor = Color(0xD8750026),) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(id = R.string.add_new_animal))
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
            items(state.animals.size) { index ->
                AnimalItem(
                    state = state,
                    index = index,
                    onEvent = onEvent
                )
            }
        }
    }
}

@Composable
fun AnimalItem(
    state: AnimalState,
    index: Int,
    onEvent: (AnimalsEvent) -> Unit
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
                text = state.animals[index].type,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = state.animals[index].amount,
                fontSize = 16.sp,
                color = Color(0xD8750026)
            )
        }
        IconButton(
            onClick = {
                onEvent(AnimalsEvent.DeleteAnimal(state.animals[index]))
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = stringResource(id = R.string.delete_animal),
                modifier = Modifier.size(35.dp),
                tint = Color(0xD8750026)
            )
        }
    }
}