package me.nasiri.areca.peresentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.nasiri.areca.R
import me.nasiri.areca.domain.util.setDollar
import me.nasiri.areca.domain.util.setShimmer


@Composable
fun ItemGrid() {
    Box(
        modifier = Modifier
            .size(250.dp, 340.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { }
            .background(Color.LightGray)
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(Color.LightGray)
                .setShimmer(true),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        IconButton(
            onClick = { },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                tint = Color.Gray,
                painter = painterResource(id = R.drawable.favorite_outline),
                contentDescription = "favorite"
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(Color.Gray)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(3f)) {
                    Text(
                        text = "Title", fontSize = 24.sp,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(text = setDollar(125451))
                }
                IconButton(modifier = Modifier.weight(1f), onClick = { }) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(RoundedCornerShape(45))
                            .background(Color.LightGray)
                            .padding(4.dp),
                        tint = Color.Gray,
                        painter = painterResource(id = R.drawable.plus_solid),
                        contentDescription = "Add to card"
                    )
                }
            }
        }
    }
}


@Composable
fun ItemGridLoadMore() {
    Box(
        modifier = Modifier
            .size(250.dp, 340.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { }
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
        content = { Text(text = "Show More") }
    )
}


@Composable
fun ItemList() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { }
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp))
                .weight(1.5f)
                .background(Color.LightGray),
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(6.dp)
                .weight(2f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Title", fontSize = 24.sp,
                style = MaterialTheme.typography.titleMedium
            )

            Text(text = setDollar(125451))

        }
        IconButton(
            onClick = { }, modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.favorite_outline),
                contentDescription = null
            )
        }
    }
}


// Sections
