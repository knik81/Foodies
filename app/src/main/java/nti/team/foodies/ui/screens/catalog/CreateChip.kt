package nti.team.foodies.ui.screens.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.team.entity.Categories
import nti.team.foodies.ui.theme.Orange

//создание чипов с категорией
@SuppressLint("SuspiciousIndentation")
@Composable
fun CreateChip(
    chipItemList: List<Categories>,
    selectCategory_id: Int,
    onClick: (Int) -> Unit
) {

    //перерисовка
    var selectedItem by remember {
        mutableStateOf(
            if (chipItemList.isNotEmpty()) {
                onClick(selectCategory_id)
                val test = chipItemList.filter { it.id == selectCategory_id}
                test[0]
            } else ""
        )
    }


    //отрисовка чипов
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(chipItemList) { category ->
            FilterChip(
                modifier = Modifier
                    .padding(end = 10.dp),
                selected = (
                        category == selectedItem),
                onClick = {
                    if (selectedItem != category)
                       // onClick(category)
                    selectedItem = category
                    category.id?.let { onClick(it) }
                },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Orange,
                    selectedLabelColor = Color.White,
                    labelColor = Color.Black,
                    containerColor = Color.White
                ),
                label = { Text(category.name.toString(), fontSize = 15.sp) },
            )
        }
    }
}