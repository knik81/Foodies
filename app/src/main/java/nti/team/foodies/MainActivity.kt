package nti.team.foodies

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import nti.team.entity.Product
import nti.team.foodies.ui.navigation.NavHostSetup
import nti.team.foodies.ui.navigation.Screens
import nti.team.foodies.ui.theme.FoodiesTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //private val viewModel: CatalogViewModel by viewModels()


    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Nik", "onCreate")


        setContent {
            FoodiesTheme {
                val navController = rememberNavController()
                NavHostSetup(navHostController = navController)
                navController.popBackStack(Screens.CatalogScreen.route, true)
                //if(navController.currentBackStackEntry)

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodiesTheme {
        Greeting("Android")
    }
}