package me.nasiri.areca.peresentation

import me.nasiri.areca.data.model.BNavigationItem
import me.nasiri.areca.data.model.BoardData
import me.nasiri.areca.data.model.DNavigationItem

data class MainState(
    val bNavItem: List<BNavigationItem> = BNavigationItem.get(),
    val dNavItem: List<DNavigationItem> = DNavigationItem.get(),
    val boardListData: List<BoardData> = BoardData.get()
)
