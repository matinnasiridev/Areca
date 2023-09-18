package me.nasiri.areca.domain.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
fun CoroutineScope.updateState(state:PagerState, action:Int) {
    launch {
        state.animateScrollToPage(action)
    }
}