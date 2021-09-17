package tj.livo.phoenix.ui.screen

import android.content.Context
import android.text.Layout
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
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

    Image(
        painter = painterResource(
            id = R.drawable.background
        ),
        contentScale = ContentScale.Crop,
        contentDescription = "background",
        modifier = Modifier.fillMaxSize()
    )
    HomeScreen()

}

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    Log.e("OnRecreate","True")
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
    val coroutineScope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.White.copy(0.5f),
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 8.dp,topEnd = 8.dp),
        sheetContent = {

            BottomSheetGrid(list = BottomSheet.bottomSheetCards, modifier = Modifier)
        },
    ) {
        val constarinSet = ConstraintSet {
            val openMenu = createRefFor("openMenu")

            constrain(openMenu) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            }
        }
        ConstraintLayout(
            constraintSet = constarinSet,
            modifier = Modifier.fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .fillMaxSize(0.3f)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .layoutId("openMenu")
                    .clickable {
                        coroutineScope.launch {
                            modalBottomSheetState.show()
                        }
                    }
                    .border(3.dp, Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_notifications_24),
                    contentDescription = "menu",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .fillMaxSize(0.4f)
                )

            }

        }


    }

}
