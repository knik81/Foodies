package nti.team.foodies.ui.screens.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.team.entity.Product
import nti.team.foodies.R
import nti.team.foodies.ui.theme.BackgroundCardColor

//прорисовка элемента списка продуктов
@Composable
fun ElementProduct(
    product: Product,
    amountInCart: Int,
    clickAdd: (Product) -> Unit
) {
    Column(
        modifier = Modifier
            .height(300.dp)
            .width(170.dp)
            .padding(top = 20.dp, start = 10.dp)
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(BackgroundCardColor),

        ) {
        Box {
            //вывод картинки только из ресурса.
            // Хочу проверить, есть ли ссылки на др. изображение
            if (product.image == "1.jpg")
                Image(
                    painter = painterResource(id = R.drawable.photo2),
                    contentDescription = "изображение продукта"
                )

            Row {
                if (product.price_old != null) {
                    //вывод индикаторов - тэгов
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.type_discount),
                        contentDescription = "Скидка"
                    )
                }

                if (product.tag_ids.contains(2)) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.type_vegan),
                        contentDescription = "веган"
                    )
                }
                if (product.tag_ids.contains(4)) {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.type_hot),
                        contentDescription = "острое"
                    )
                }
            }
            if (amountInCart > 0)
                Row(
                    modifier = Modifier
                        .height(150.dp)
                        .width(170.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Box {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.indicator),
                            contentDescription = amountInCart.toString(),
                            modifier = Modifier.padding(end = 5.dp)
                        )
                        Text(
                            text = amountInCart.toString(),
                            fontSize = 15.sp,
                            color = Color.White,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 6.dp, bottom = 3.dp),
                        )
                    }
                }
        }

        Text(text = product.name ?: "", fontSize = 13.sp, maxLines = 2)

        if (product.measure != null) {
            Row(Modifier.padding(top = 5.dp)) {
                Text(
                    text = (product.measure)?.toInt().toString(),
                    fontSize = 13.sp,
                    color = Color.DarkGray
                )
                Text(
                    modifier = Modifier.padding(start = 3.dp), fontSize = 13.sp,
                    text = product.measure_unit ?: "",
                    color = Color.DarkGray
                )
            }
        }

        //добавление кнопки  с ценой
        Row(
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp, bottom = 1.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                onClick = {
                    clickAdd(product)
                    //Cart.cart.add(product)
                },
                modifier = Modifier,
                //.clip(shape = RoundedCornerShape(10.dp))
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(Color.White),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (product.price_current != null)
                        Text(
                            fontSize = 14.sp,
                            text = "${product.price_current?.div(100).toString()} ₽",
                            color = Color.Black
                        )
                    if (product.price_old != null)
                        Text(
                            fontSize = 11.sp,
                            text = "${product.price_old?.div(100).toString()}₽",
                            textDecoration = TextDecoration.LineThrough,
                            modifier = Modifier.padding(start = 10.dp),
                            color = Color.Gray
                        )
                }
            }
        }

    }
}