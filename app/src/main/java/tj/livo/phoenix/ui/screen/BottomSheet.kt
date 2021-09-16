package tj.livo.phoenix.ui.screen

import android.content.ContentValues.TAG
import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tj.livo.phoenix.ui.BottomSheetCard
import tj.livo.phoenix.ui.components.VolumeChanger
import java.lang.Math.*
import kotlin.math.cos
import kotlin.math.sin

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

@ExperimentalFoundationApi
@Composable
fun BottomSheetGrid(list: List<BottomSheetCard>, modifier: Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        val volumeChangerState = remember {
            mutableStateOf(false)
        }
        val coroutineScope = rememberCoroutineScope()
          if (volumeChangerState.value) {
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
                        color = Color.Gray,
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

