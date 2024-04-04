package nti.team.foodies.ui.screens.card_food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemDescription(
    name: String,
    text: String
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            fontSize = 13.sp,
            text = name,
            color = Color.DarkGray
        )
        Text(
            fontSize = 13.sp,
            text = text,
            color = Color.Black
        )
    }

    HorizontalDivider(
        modifier = Modifier.padding(top = 5.dp),
        thickness = 2.dp,
        color = Color.LightGray
    )
}