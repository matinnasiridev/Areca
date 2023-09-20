package me.nasiri.areca.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class DNavigationItem(
    val title: String,
    val selectedItem: ImageVector,
    val unselectedItem: ImageVector,
    val badgeCount: Int? = null,
) {
    companion object {
        fun get(): List<DNavigationItem> {
            return listOf(
                DNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
                DNavigationItem("Card", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart, 8),
                DNavigationItem("Chat", Icons.Filled.Call, Icons.Outlined.Call, 3)
            )
        }
    }
}
