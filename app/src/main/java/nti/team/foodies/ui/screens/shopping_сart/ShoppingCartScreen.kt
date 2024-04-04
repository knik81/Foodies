import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import nti.team.entity.Cart
import nti.team.entity.Product
import nti.team.foodies.R
import nti.team.foodies.ui.theme.Orange

@Composable
fun ShoppingCartScreen(navController: NavHostController) {

    val productsInCart = remember {
        mutableStateListOf<Product>()
    }

    val cart = remember {
        mutableStateMapOf<Product, Int>()
    }
    if (Cart.cart.isNotEmpty())
        cart.putAll(Cart.cart)
    else cart.clear()

    val productListInCart = remember {
        mutableStateOf(cart.keys.toList())
    }
    productListInCart.value = cart.keys.toList()

    val isShowButton = remember {
        mutableStateOf(true)
    }

    Column(Modifier.padding(15.dp)) {
        Column(Modifier.padding(bottom = 40.dp)) {
            Row {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.button_back2),
                    contentDescription = "filter",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }
                )
                Box(Modifier.padding(start = 30.dp)) {
                    Text(
                        text = "Корзина",
                        color = Color.Black,
                        fontSize = 20.sp
                    )
                }
            }


            //отобразить продукты по категории
            if (productListInCart.value.isNotEmpty()) {
                LazyColumn {
                    items(productListInCart.value) { product ->
                        Box(modifier = Modifier.clickable {
                        }) {
                            //отрисовка элемента
                            ElementCart(
                                product,
                                Cart.cart[product] ?: 0
                            ) {
                                productsInCart.add(product)
                                val count = cart[product] ?: 0
                                if ((count + it) < 1) {
                                    Cart.cart.remove(product)
                                    cart.remove(product)
                                } else {
                                    Cart.cart[product] = count + it
                                    cart[product] = count + it
                                }
                                Cart.cart = cart
                            }
                        }
                    }
                }
            } else {
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Пусто, выбирите блюдо в каталоге :)",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.width(200.dp),
                        textAlign = TextAlign.Center
                    )
                }
                isShowButton.value = false
            }
        }
    }
    Row(
        Modifier
            .fillMaxSize()
            .padding(end = 15.dp), verticalAlignment = Alignment.Bottom
    ) {
        if (isShowButton.value)
            Button(
                onClick = {
                    Cart.cart.clear()
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
                        text = "Заказать за  ",
                        color = Color.White,
                        fontSize = 15.sp
                    )
                    var totalSum = 0.0
                    cart.forEach { cartIt ->
                        val prise: Double = ((cartIt.key.price_current ?: 0).toDouble() / 100)
                        totalSum += prise * cartIt.value
                    }
                    Text(
                        text = "$totalSum ₽",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
    }
}