package tj.livo.phoenix.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tj.livo.phoenix.ui.BottomSheetCard

object BottomSheet {
    val bottomSheetCards = listOf(
        BottomSheetCard(Icons.Default.Star, "Оставить отзыв"),
        BottomSheetCard(Icons.Default.Notifications, "Вызвать официанта"),
        BottomSheetCard(Icons.Default.Home, "Открыть меню"),
        BottomSheetCard(Icons.Default.ThumbUp, "Открыть картинку"),
        BottomSheetCard(Icons.Default.Settings, "Открыть PDF"),
        BottomSheetCard(Icons.Default.Call, "Поделиться"),
        BottomSheetCard(Icons.Default.Check, "Закрыть"),
    )
}

@ExperimentalFoundationApi
@Composable
fun BottomSheetGrid(list: List<BottomSheetCard>, modifier: Modifier) {
    LazyVerticalGrid(cells = GridCells.Fixed(4)) {
        items(list) { item -> BottomSheetCards(item = item, modifier = modifier) }
    }
}

@Composable
fun BottomSheetCards(item: BottomSheetCard, modifier: Modifier) {

    val multiplier = remember { mutableStateOf(1.3f) }
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp)
            .background(
                color = Color.Black.copy(0.5f),
                shape = RoundedCornerShape(6.dp)
            )
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
                        color = Color.Gray,
                    )
                    .padding(4.dp),
                colorFilter = ColorFilter.tint(Color.White),
                contentScale = ContentScale.Fit

            )
            Text(
                text = item.title,
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

@ExperimentalFoundationApi
@Preview
@Composable
fun CardPreview() {
//    BottomSheetCards(modifier = Modifier)
    BottomSheetGrid(list = BottomSheet.bottomSheetCards, modifier = Modifier)
}

