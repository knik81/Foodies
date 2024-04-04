package nti.team.foodies.ui.screens.card_food

import ButtonsCartElement
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import nti.team.entity.Cart
import nti.team.entity.Product
import nti.team.foodies.R
import nti.team.foodies.ui.theme.Orange

@Composable
fun CardFoodScreen(
    navController: NavHostController,
    productSet: Product?,
    viewModel: CardFoodViewModel = hiltViewModel()
) {

    //Log.d("Nik", "productSet $productSet")
    if (productSet != null)
        viewModel.productSaver = productSet

    val product = remember {
        mutableStateOf(viewModel.productSaver)
    }


    val count = remember {
        mutableIntStateOf(Cart.cart[product.value] ?: 0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 15.dp, bottom = 5.dp, end = 15.dp)
    ) {
        Column(
            Modifier
                .padding(bottom = 50.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Box {
                //отображение продукта
                Image(
                    painter = painterResource(id = R.drawable.photo),
                    contentDescription = "изображение продукта"
                )
                //иконка обратно
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.button_back),
                    contentDescription = "filter",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Row(Modifier.padding(top = 10.dp, start = 100.dp)) {
                    if (product.value?.price_old != null) {
                        //вывод индикаторов - тэгов
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.type_discount),
                            contentDescription = "Скидка"
                        )
                    }

                    if (product.value?.tag_ids?.contains(2) == true) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.type_vegan),
                            contentDescription = "веган"
                        )
                    }
                    if (product.value?.tag_ids?.contains(4) == true) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.type_hot),
                            contentDescription = "острое"
                        )
                    }
                }
            }
            Text(
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 30.sp,
                text = product.value?.name ?: "",
                color = Color.Black
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 13.sp,
                text = product.value?.description ?: "",
                color = Color.DarkGray
            )

            HorizontalDivider(
                modifier = Modifier.padding(top = 5.dp),
                thickness = 2.dp,
                color = Color.LightGray
            )


            ItemDescription("Вес", "${product.value?.measure} ${product.value?.measure_unit}")

            ItemDescription("Энерг. ценность", "${product.value?.energy_per_100_grams} ккал")

            ItemDescription("Белки", "${product.value?.proteins_per_100_grams} г")

            ItemDescription("Жиры", "${product.value?.fats_per_100_grams} г")

            ItemDescription("Углеводы", "${product.value?.carbohydrates_per_100_grams} г")

        }
    }
    Row(
        Modifier
            .fillMaxSize()
            .padding(end = 15.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        if (count.intValue < 1)
            Button(
                onClick = {
                    count.intValue += 1
                    if (product.value != null)
                        Cart.cart[product.value!!] = count.intValue
                },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(10.dp))
                    .padding(15.dp, bottom = 5.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(Orange),

                ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "В корзину за ",
                        color = Color.White,
                        fontSize = 13.sp
                    )
                    Text(
                        text = "${product.value?.price_current?.div(100).toString()} ₽",
                        color = Color.White,
                        fontSize = 17.sp
                    )
                }
            }
        else {
            ButtonsCartElement(count.intValue, true) {
                val count1 = Cart.cart[product.value] ?: 0
                if ((count1 + it) < 1) {
                    Cart.cart.remove(product.value)
                } else {
                    if (product.value != null)
                        Cart.cart[product.value!!] = count1 + it
                }
                count.intValue = Cart.cart[product.value] ?: 0
            }
        }
    }
}


