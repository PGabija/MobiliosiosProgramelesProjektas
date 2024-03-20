package eif.viko.lt.gposkaite.myFarm.presentation.Animals

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import eif.viko.lt.gposkaite.R
import eif.viko.lt.gposkaite.myFarm.data.Animals.AnimalDatabase
import eif.viko.lt.gposkaite.myFarm.model.DrawerItem
import eif.viko.lt.gposkaite.myFarm.ui.theme.MyFarmTheme
import eif.viko.lt.gposkaite.myFarm.util.NavigationUtils
import kotlinx.coroutines.launch

class AnimalsActivity : ComponentActivity() {
    private lateinit var scaffoldState: ScaffoldState

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AnimalDatabase::class.java,
            "animals.db"
        ).build()
    }

    private val viewModel by viewModels<AnimalViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AnimalViewModel(database.dao) as T
                }
            }
        }
    )

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
                        NavigationUtils.navigateToHome(context)
                        finish()
                    },
                    DrawerItem(icon = Icons.Default.Favorite, label = stringResource(id = R.string.animals)) {
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
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(stringResource(id = R.string.animals))
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            scaffoldState.drawerState.open()
                                        }
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Animals")
                                }
                            }
                        )
                    },
                    drawerContent = {
                        DrawerContent(items = items)
                    },
                    content = {
                        MyFarmContent()
                    }
                )
            }
        }
    }

    @Composable
    fun MyFarmContent() {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val state by viewModel.state.collectAsState()
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "AnimalsScreen") {
                composable("AnimalsScreen") {
                    AnimalsScreen(
                        state = state,
                        navController = navController,
                        onEvent = viewModel::onEvent
                    )
                }
                composable("AddAnimalScreen") {
                    AddAnimalScreen(
                        state = state,
                        navController = navController,
                        onEvent = viewModel::onEvent
                    )
                }
            }
        }
    }

    @Composable
    fun DrawerContent(items: List<DrawerItem>) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier.height(24.dp))

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
    fun DrawerItemRow(icon: ImageVector?, label: String, onClick: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { onClick() }
        ) {
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp))
                Spacer(
                    modifier = Modifier.width(16.dp))
            }

            Text(
                text = label,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}