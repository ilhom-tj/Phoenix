package tj.livo.phoenix.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import tj.livo.phoenix.R
import tj.livo.phoenix.navigation.NavDestination


@Preview(showSystemUi = true)
@Composable
fun LoginScreen(
    navController: NavController? = null
) {
    val login = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Scaffold() {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),

        verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.fenix),
                contentDescription = "fenix",
                modifier = Modifier.padding(16.dp).padding(bottom = 25.dp)
            )


            TextField(value = login.value, onValueChange = {
                login.value = it
            }, label = {
                Text(text = "Login")
            },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = password.value,
                visualTransformation = PasswordVisualTransformation(),
                onValueChange = {
                    login.value = it
                },
                label = {
                    Text(text = "Password")
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFF6600),
                ),
                onClick = {
                          navController?.navigate(NavDestination.Main.route)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Enter",
                    color = Color.White,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(5.dp)
                )
            }
        }
    }
}