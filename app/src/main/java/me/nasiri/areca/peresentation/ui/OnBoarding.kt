package me.nasiri.areca.peresentation.ui

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.burnoo.cokoin.viewmodel.getViewModel
import me.nasiri.areca.R
import me.nasiri.areca.data.model.BoardData
import me.nasiri.areca.domain.util.updateState
import me.nasiri.areca.peresentation.MainVM


/**
 * This Composable Function use @param[data] Create an screen  and show
 * Indicators In to
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoarding(
    data: List<BoardData>,
    boardingEnd: MainVM.() -> Unit
) {

    val scope = rememberCoroutineScope()
    val state = rememberPagerState()
    val index = state.currentPage
    val viewModel = getViewModel<MainVM>()

    Column(modifier = Modifier.fillMaxSize()) {

        // Main Content:
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f),
            pageCount = data.size,
            state = state
        ) {
            OnBoardingItem(singleData = data[it])
        }

        // Bottom Section Of Board:
        BottomSection(size = data.size, index = index) {
            if (index == data.size - 1) // End Of Boarding:
                boardingEnd(viewModel)
            else // navigation:
                scope.updateState(state, index + 1)
        }
    }
}

@Composable
fun OnBoardingItem(singleData: BoardData) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            Image(
                painter = painterResource(id = singleData.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    // todo Remove This Fun
                    .background(MaterialTheme.colorScheme.primary)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp)
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(topStart = 80f, topEnd = 80f))
                    .background(MaterialTheme.colorScheme.background), content = { })
        }
        Text(text = singleData.title, style = MaterialTheme.typography.titleMedium)
        Text(text = singleData.des)
    }
}


@Composable
fun BottomSection(size: Int, index: Int, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Show Indicators
        Indicators(size = size, index = index)

        Spacer(modifier = Modifier.height(24.dp))

        // Next Btn For navigate on slid or finish boarding save in shared
        FloatingActionButton(
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Text(
                text = if (size - 1 != index) stringResource(id = R.string.next) else stringResource(
                    id = R.string.start
                )
            )
        }
    }
}

@Composable
fun Indicators(size: Int, index: Int) {
    // Create An Row View Group For Hold Indicators
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // For each One Of Page Repeat Indicator()
        repeat(size) {
            Indicator(it == index)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean = false) {

    // width Change Animation use animateDpAsState():
    val width = animateDpAsState(
        targetValue = if (isSelected) 25.dp else 10.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = ""
    )
    // Create An Circle For Single Page:
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .height(10.dp)
            .width(width.value)
            .background(
                if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onBackground.copy(
                    alpha = 0.5f
                )
            )
    )
}