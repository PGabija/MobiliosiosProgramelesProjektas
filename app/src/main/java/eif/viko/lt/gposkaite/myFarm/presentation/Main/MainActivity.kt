package eif.viko.lt.gposkaite.myFarm.presentation.Main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.room.Room
import eif.viko.lt.gposkaite.R
import eif.viko.lt.gposkaite.myFarm.data.Adds.AdvertisementDatabase
import eif.viko.lt.gposkaite.myFarm.model.DrawerItem
import eif.viko.lt.gposkaite.myFarm.ui.theme.MyFarmTheme
import eif.viko.lt.gposkaite.myFarm.util.NavigationUtils
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var scaffoldState: ScaffoldState

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AdvertisementDatabase::class.java,
            "adds.db"
        ).build()
    }
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            scaffoldState = rememberScaffoldState()
            MyFarmTheme {
                val scope = rememberCoroutineScope()
                val context = LocalContext.current

                val items = listOf(
                    DrawerItem(icon = Icons.Default.Person, label = stringResource(id = R.string.home)) {

                    },
                    DrawerItem(icon = Icons.Default.Favorite, label = stringResource(id = R.string.animals)) {
                        NavigationUtils.navigateToAnimals(context)
                        finish()
                    },
                    DrawerItem(icon = Icons.Default.Settings, label = stringResource(id = R.string.devices)) {
                        NavigationUtils.navigateToDevices(context)
                        finish()
                    },
                    DrawerItem(icon = Icons.Default.Person, label = stringResource(id = R.string.employees)) {
                        NavigationUtils.navigateToEmployees(context)
                        finish()
                    },
                    DrawerItem(icon = null, label = stringResource(id = R.string.sign_out)) {
                        NavigationUtils.signOut(context)
                        finish()
                    }
                )

                val drawerState = rememberDrawerState(DrawerValue.Closed)

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(id = R.string.home))
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            scaffoldState.drawerState.open()
                                        }
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                                }
                            }
                        )
                    },
                    drawerContent = {
                        DrawerContent(items = items)
                    },
                    content = {
                        HomeScreen()
                    }
                )
            }
        }
    }

    @Composable
    fun DrawerContent(items: List<DrawerItem>) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Spacer(modifier = Modifier.height(24.dp))

            items.forEach { item ->
                DrawerItemRow(
                    icon = item.icon,
                    label = item.label,
                    onClick = {
                        item.onClick()
                        scaffoldState.drawerState
                    }
                )
            }
        }
    }

    @Composable
    fun DrawerItemRow(icon: ImageVector?, label: String, onClick: () -> Unit) {  Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onClick() }
        ) {
            if (icon != null) {
                Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp))
                Spacer(modifier = Modifier.width(16.dp))
            }

            Text(
                text = label,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}