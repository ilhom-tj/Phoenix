package tj.livo.phoenix.ui.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import android.media.AudioManager
import android.util.Log
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContentProviderCompat

import androidx.core.content.ContentProviderCompat.requireContext
import tj.livo.phoenix.R


@Composable
fun VolumeChanger() {
    var size by remember { mutableStateOf(Size.Zero) }
    var offsetX by remember { mutableStateOf(1f) }
    val audioManager = LocalContext.current.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val current = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    val soundColorState = remember {
        mutableStateOf(Color.White)
    }
    val progressState = remember {
        mutableStateOf(current.toFloat() / max)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                size = it.size.toSize()
            }
    ) {
        Box {
            LinearProgressIndicator(
                backgroundColor = Color.Black.copy(0.7f),
                color = Color.White, modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            offsetX += delta
                            progressState.value = (offsetX / size.width)
                            if (progressState.value > 0.08){
                                soundColorState.value = Color.Black
                            }else{
                                soundColorState.value = Color.White
                            }
                            audioManager.setStreamVolume(
                                AudioManager.STREAM_MUSIC,
                                (max * progressState.value).toInt(),
                                0
                            )
                        }
                    )
                    .height(35.dp),
                progress = progressState.value
            )
            Icon(
                painter = painterResource(id = R.drawable.sound),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterStart),
                tint = soundColorState.value
            )
        }
        Spacer(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White.copy(0.5f))
        )
    }

}