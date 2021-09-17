package tj.livo.phoenix.ui.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.*
import kotlinx.coroutines.launch
import tj.livo.phoenix.ui.BottomSheetCard
import tj.livo.phoenix.ui.components.VolumeChanger

object BottomSheet {
    val bottomSheetCards = listOf(
        BottomSheetCard(Icons.Default.Star, "Оставить отзыв", "sound"),
        BottomSheetCard(Icons.Default.Notifications, "Вызвать официанта"),
        BottomSheetCard(Icons.Default.Home, "Открыть меню"),
        BottomSheetCard(Icons.Default.ThumbUp, "Открыть картинку"),
        BottomSheetCard(Icons.Default.Settings, "Открыть PDF"),
        BottomSheetCard(Icons.Default.Call, "Поделиться"),
        BottomSheetCard(Icons.Default.Check, "Закрыть"),
    )
}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@Composable
fun BottomSheetGrid(list: List<BottomSheetCard>, modifier: Modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val volumeChangerState = remember {
            mutableStateOf(false)
        }
        val coroutineScope = rememberCoroutineScope()
        AnimatedVisibility(visible = volumeChangerState.value) {
            VolumeChanger()
        }
        LazyVerticalGrid(cells = GridCells.Fixed(4)) {
            items(list) { item ->
                BottomSheetCards(item = item, modifier = modifier, onClick = {
                    coroutineScope.launch {
                        if (item.type == "sound") {
                            volumeChangerState.value = !volumeChangerState.value
                        }
                    }
                })
            }
        }
    }

}

@Composable
fun BottomSheetCards(item: BottomSheetCard, modifier: Modifier, onClick: () -> Unit) {

    var isSelected by remember {
        mutableStateOf(false)
    }
    var iconBackgroundState by remember {
        mutableStateOf(Color.Gray)
    }
    val multiplier = remember { mutableStateOf(1.3f) }
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .background(
                color = Color.Black.copy(0.5f),
                shape = RoundedCornerShape(6.dp)
            )
            .clickable {
                isSelected = !isSelected
                iconBackgroundState = if (isSelected)
                    Color(0xFF1D8DF3)
                else
                    Color.Gray
                onClick()
            }
    ) {


        Column(
            modifier = modifier
                .padding(8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround
        ) {

            Image(
                imageVector = item.icon,
                contentDescription = "",
                modifier = modifier
                    .fillMaxWidth(0.4f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .background(
                        color = iconBackgroundState,
                    )
                    .padding(4.dp),
                colorFilter = ColorFilter.tint(Color.White),
                contentScale = ContentScale.Fit

            )
            Text(
                text = item.title,
                maxLines = 2,
                modifier = modifier
                    .padding(top = 8.dp),
                style = LocalTextStyle.current.copy(
                    fontSize = LocalTextStyle.current.fontSize * multiplier.value
                ),
                onTextLayout = {
                    if (it.hasVisualOverflow) {
                        multiplier.value *= .8f // you can tune this constant
                    }
                },
                color = Color.White
            )
        }

    }
}

