package nti.team.foodies.ui.navigation

import ShoppingCartScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nti.team.entity.Product
import nti.team.foodies.ui.screens.card_food.CardFoodScreen
import nti.team.foodies.ui.screens.catalog.CatalogScreen
import nti.team.foodies.ui.screens.onboarding.OnboardingScreen


@Composable
//навигация по экранам
fun NavHostSetup(
    navHostController: NavHostController
) {
    var product: Product? = null
    NavHost(
        navController = navHostController,
        startDestination = Screens.OnboardingScreen.route,

    ) {
        composable(route = Screens.OnboardingScreen.route) {
            OnboardingScreen(navController = navHostController)
        }
        composable(route = Screens.CatalogScreen.route) {
            CatalogScreen(navController = navHostController) { setProduct ->
                product = setProduct
            }
        }
        composable(route = Screens.CardFoodScreen.route) {
            CardFoodScreen(navController = navHostController, productSet = product)
        }

        composable(route = Screens.ShoppingCartScreen.route) {
            ShoppingCartScreen(navController = navHostController)
        }
    }
}