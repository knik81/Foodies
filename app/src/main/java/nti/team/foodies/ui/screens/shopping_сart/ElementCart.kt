import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.team.entity.Product
import nti.team.foodies.R
import nti.team.foodies.ui.theme.BackgroundCardColor

//прорисовка элемента списка продуктов
@Composable
fun ElementCart(
    product: Product,
    amountProduct: Int,
    click: (Int) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 1.dp, end = 1.dp)
            .height(110.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(BackgroundCardColor),
    ) {
        //картинка
        if (product.image == "1.jpg")
            Image(
                painter = painterResource(id = R.drawable.photo2),
                contentDescription = "изображение продукта"
            )

        Column(Modifier.fillMaxHeight()) {
            Text(
                text = product.name ?: "",
                fontSize = 15.sp,
                color = Color.DarkGray,
                maxLines = 2,
                modifier = Modifier.padding(10.dp)
            )

            //кнопки + цена
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {

                //блок кнопок
                ButtonsCartElement(amountProduct) {
                    click(it)
                }

                //цена
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${product.price_current?.div(100)} ₽",
                        maxLines = 1,
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                    if (product.price_old != null)
                        Text(
                            fontSize = 13.sp,
                            text = "${product.price_old?.div(100).toString()}₽",
                            textDecoration = TextDecoration.LineThrough,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 10.dp),
                            color = Color.Gray
                        )
                }
            }
        }
    }

}
