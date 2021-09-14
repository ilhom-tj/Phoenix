package tj.livo.phoenix.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tj.livo.phoenix.R


@Composable
fun ModalCard(){
    Card(
        modifier = Modifier
            .size(150.dp, 100.dp)
            .padding(8.dp)
            .background(Color.Black.copy(0.0f)),
        backgroundColor = Color.Black.copy(0.6f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.5f)
                    .background(Color.White.copy(0.25f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sound),
                    contentDescription = "sound",
                )
            }
            Text(
                text = "Уровень громкости",
                color = Color.White,
                fontSize = 12.sp
            )
        }
    }
}