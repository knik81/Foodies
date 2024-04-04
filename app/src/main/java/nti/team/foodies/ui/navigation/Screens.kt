package nti.team.foodies.ui.navigation

sealed class Screens(val route: String) {
    data object OnboardingScreen: Screens(Routs.ONBOARDING)
    data object CatalogScreen: Screens(Routs.CATALOG)
    data object CardFoodScreen: Screens(Routs.CARD_FOOD)
    data object ShoppingCartScreen: Screens(Routs.SHOPPING_CART)
}



