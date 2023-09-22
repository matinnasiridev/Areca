package me.nasiri.areca.peresentation.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import me.nasiri.areca.peresentation.MainState


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun MainView(state: MainState = MainState()) {

    val drawState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var drawSelect by rememberSaveable {
        mutableIntStateOf(0)
    }
    var bottomSelect by rememberSaveable {
        mutableIntStateOf(0)
    }

    ModalNavigationDrawer(
        drawerContent = {
            /* todo fixed this draw use ui */
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(12.dp))
                state.dNavItem.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        modifier = Modifier.padding(12.dp),
                        icon = {
                            Icon(
                                imageVector = if (index == drawSelect) item.selectedItem else item.unselectedItem,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(text = item.title) },
                        badge = {
                            item.badgeCount?.let {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        selected = index == drawSelect,
                        onClick = {
                            drawSelect = index
                            /* todo navigation Task */
                            scope.launch {
                                drawState.close()
                            }
                        }
                    )
                }
            }
        },
        drawerState = drawState
    ) {
        Scaffold(
            modifier = Modifier.padding(10.dp),
            topBar = {
                TopAppBar(
                    title = { },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawState.open()
                                }
                            },
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(25))
                                .background(Color.Black)
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                )
            },
            bottomBar = {
                NavigationBar {
                    state.bNavItem.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = bottomSelect == index,
                            onClick = {
                                bottomSelect = index
                            },
                            icon = {
                                BadgedBox(badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.haveMES) {
                                        Badge()
                                    }
                                }) {
                                    Icon(
                                        imageVector = if (bottomSelect == index) item.selectedItem else item.unselectedItem,
                                        contentDescription = item.title
                                    )
                                }
                            }
                        )
                    }
                }
            }
        ) { }
    }
}