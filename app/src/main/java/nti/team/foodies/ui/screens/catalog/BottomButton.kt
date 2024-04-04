package nti.team.foodies.ui.screens.catalog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import nti.team.entity.Product
import nti.team.foodies.R
import nti.team.foodies.ui.navigation.Screens
import nti.team.foodies.ui.theme.Orange

//нижняя кнопка перехода в корзину
@Composable
fun BottomButton(
    navController: NavHostController,
    cart : MutableMap<Product, Int>
){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        var totalSum = 0.0
        cart.forEach { cartIt ->
            val prise: Double =
                ((cartIt.key.price_current ?: 0).toDouble() / 100)
            totalSum += prise * cartIt.value
        }


        Button(
            onClick = {
                navController.navigate(Screens.ShoppingCartScreen.route)
            },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .fillMaxWidth(),
            shape = RoundedCornerShape(20),
            colors = ButtonDefaults.buttonColors(Orange),
        ) {
            Icon(
                ImageVector.vectorResource(R.drawable.cart),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.padding(end = 10.dp)
            )

            Text(
                text = "$totalSum",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}