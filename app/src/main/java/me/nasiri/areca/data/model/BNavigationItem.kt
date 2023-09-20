package me.nasiri.areca.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class BNavigationItem(
    val title: String,
    val selectedItem: ImageVector,
    val unselectedItem: ImageVector,
    val haveMES: Boolean,
    val badgeCount: Int? = null,
) {
    companion object {
        fun get(): List<BNavigationItem> {
            return listOf(
                BNavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home, false),
                BNavigationItem(
                    "Card",
                    Icons.Filled.ShoppingCart,
                    Icons.Outlined.ShoppingCart,
                    false,
                    8
                ),
                BNavigationItem("Chat", Icons.Filled.Call, Icons.Outlined.Call, true)
            )
        }
    }
}

