package nti.team.foodies.ui.screens.catalog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.team.foodies.ui.screens.catalog.entity.SelectorTopRow


//текст при пустоте
@Composable
fun EmptyText(
    selectorTopRow: SelectorTopRow
) {
    Row(
        Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (selectorTopRow) {
            SelectorTopRow.ICONS ->
                Column {
                    Text(
                        text = "таких блюд нет :(",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.width(250.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Попробуйте изменить фильтры",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.width(250.dp),
                        textAlign = TextAlign.Center
                    )
                }

            SelectorTopRow.SEARCH ->
                Text(
                    text = "Ничего не нашлось :(",
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.width(250.dp),
                    textAlign = TextAlign.Center
                )

            SelectorTopRow.ERROR ->
                Column {
                    Text(
                        text = "Ошибка загрузки данных",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.width(250.dp),
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Возможно проблемы с интернетом",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.width(250.dp),
                        textAlign = TextAlign.Center
                    )
                }

            SelectorTopRow.LOADING -> {
                Column {
                    Text(
                        text = "Загрузка",
                        color = Color.Gray,
                        fontSize = 16.sp,
                        modifier = Modifier.width(250.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}