package nti.team.foodies.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import nti.team.foodies.ui.navigation.Screens
import kotlinx.coroutines.delay
import nti.team.foodies.ui.theme.ChangeColor
import nti.team.foodies.ui.theme.Orange


@Composable
fun OnboardingScreen(
    navController: NavHostController
) {

    // Log.d("Nik", "OnboardingScreen")


    //перекрасил статус
    ChangeColor(LocalView.current, Orange)

    //отображение логотипа на превью
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Orange),
        Alignment.Center
    ) {
        //обображение логотипа и смена его цвета на белый

        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.Asset("Animate.json"))
        LottieAnimation(
            composition = composition
        )
        LaunchedEffect(Unit) {
            delay(2000)
            //delay(20)
            navController.popBackStack()
            navController.navigate(Screens.CatalogScreen.route)
        }
    }
}





