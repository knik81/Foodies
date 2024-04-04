package nti.team.foodies.ui.screens.card_food

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import nti.team.entity.Product
import javax.inject.Inject


@HiltViewModel
class CardFoodViewModel @Inject constructor() : ViewModel() {
    var productSaver: Product? = null
}

