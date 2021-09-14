package tj.livo.phoenix.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.android.material.internal.TextScale
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import tj.livo.phoenix.R


@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun MainScreen(navController: NavController? = null) {


    val modalBottomState = remember {
        mutableStateOf(false)
    }
    HomeScreen()


}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Expanded)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        sheetBackgroundColor = Color.White.copy(0.5f),
        sheetShape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {

            LazyVerticalGrid(cells = GridCells.Fixed(4), content = {
                items(5) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.35f)
                            .height(100.dp)
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
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            })

        },
        sheetPeekHeight = 0.dp
    ) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
            Image(
                painter = painterResource(
                    id = R.drawable.background
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "background",
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.17f)
                    .fillMaxWidth(0.4f)
                    .padding(end = 16.dp)
                    .clip(CircleShape)
                    .clickable {
                        coroutineScope.launch {
                            if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                                bottomSheetScaffoldState.bottomSheetState.expand()
                            } else {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                        }

                    }
                    .border(3.dp, Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_notifications_24),
                    contentDescription = "menu",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.fillMaxSize(0.4f)
                )

            }
        }
    }
}
