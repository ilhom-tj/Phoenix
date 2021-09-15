package tj.livo.phoenix.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
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
            verticalArrangement = SpaceBetween
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = "",
                modifier = modifier
                    .clip(CircleShape)
                    .background(
                        color = Color.Gray,
                    )
                    .padding(16.dp),
                tint = Color.White
            )
            Text(
                text = item.title,
                modifier = modifier
                    .padding(top = 8.dp),
                fontSize = TextUnit.Unspecified,
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

