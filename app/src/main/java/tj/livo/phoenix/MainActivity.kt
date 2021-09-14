package tj.livo.phoenix

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tj.livo.phoenix.navigation.NavDestination
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
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContent {
            val navController = rememberNavController()
            PhoenixTheme {
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

