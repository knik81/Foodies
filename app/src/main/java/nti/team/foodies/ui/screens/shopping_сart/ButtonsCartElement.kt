

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.team.foodies.ui.theme.Orange

//блок кнопок
@Composable
fun ButtonsCartElement(
    amountProduct: Int,
    isFillMaxWidth: Boolean = false,
    click: (Int) -> Unit
) {
    if (!isFillMaxWidth)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonsOnly(amountProduct) {
                click(it)
            }
        }
    else
        Row(
            Modifier.fillMaxWidth(),//.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonsOnly(amountProduct) {
                click(it)
            }
        }
}

@Composable
fun ButtonsOnly(
    amountProduct: Int,
    click: (Int) -> Unit
) {
    Button(
        onClick = {
            click(-1)
        },
        //modifier = Modifier.width(60.dp),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(Color.White),
    ) {
        Text(
            fontSize = 25.sp,
            text = "-",
            modifier = Modifier,
            color = Orange
        )
    }

    Text(
        fontSize = 25.sp,
        text = amountProduct.toString(),//amount.intValue.toString(),
        modifier = Modifier.padding(8.dp),
        color = Color.Black
    )

    Button(
        onClick = {
            click(1)
        },
       // modifier = Modifier.width(60.dp),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(Color.White),
    ) {
        Text(
            fontSize = 25.sp,
            text = "+",
            modifier = Modifier,
            color = Orange
        )

    }
}