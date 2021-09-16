package tj.livo.phoenix

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import tj.livo.phoenix.navigation.NavDestination
import tj.livo.phoenix.ui.screen.BottomSheet
import tj.livo.phoenix.ui.screen.BottomSheetGrid
import tj.livo.phoenix.ui.screen.LoginScreen
import tj.livo.phoenix.ui.screen.MainScreen
import tj.livo.phoenix.ui.theme.PhoenixTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContent {
            val navController = rememberNavController()
            val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
                bottomSheetState = BottomSheetState(BottomSheetValue.Expanded)
            )
            val coroutineScope = rememberCoroutineScope()
            PhoenixTheme {
                BottomSheetScaffold(
                    sheetBackgroundColor = Color.White.copy(0.5f),
                    sheetShape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
                    scaffoldState = bottomSheetScaffoldState,
                    sheetContent = {
                        BottomSheetGrid(list = BottomSheet.bottomSheetCards, modifier = Modifier)
                    },
                    sheetPeekHeight = 0.dp
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavDestination.Login.route
                    ) {

                        composable(NavDestination.Login.route) {
                            LoginScreen(navController = navController)
                        }
                        composable(NavDestination.Main.route) {
                            MainScreen(navController = navController)
                        }
                    }
                }

            }
        }
    }
}

