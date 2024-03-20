package eif.viko.lt.gposkaite.myFarm.model

import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerItem(
    val icon: ImageVector?,
    val label: String,
    val onClick: () -> Unit
)
