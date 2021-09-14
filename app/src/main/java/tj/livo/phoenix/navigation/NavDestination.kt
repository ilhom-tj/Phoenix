package tj.livo.phoenix.navigation

sealed class NavDestination(val route : String) {
    object Main : NavDestination("main")
    object Login : NavDestination("login")
}