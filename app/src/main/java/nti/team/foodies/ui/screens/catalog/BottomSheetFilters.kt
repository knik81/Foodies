package nti.team.foodies.ui.screens.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nti.team.entity.Tags
import nti.team.foodies.ui.screens.catalog.entity.CheckedFilters
import nti.team.foodies.ui.theme.Orange


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetFilters(
    checkedFiltersList: List<CheckedFilters>?,
    sheetState: SheetState,
    clickClose: (Boolean) -> Unit,
    clickCheckBox: (Tags, Boolean) -> List<CheckedFilters>
) {

    ModalBottomSheet(
        onDismissRequest = { clickClose(false) },
        sheetState = sheetState
    ) {
        Text(
            "Подобрать блюда", fontSize = 25.sp, color = Color.Black,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 15.dp)
        )

        val isCheckedList = remember { mutableStateOf(checkedFiltersList) }

        //отобразить фильры
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp)
        ) {
            if (!isCheckedList.value.isNullOrEmpty())
                items(isCheckedList.value!!) { checkedFilter ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = checkedFilter.tags.name ?: "", fontSize = 16.sp)
                        Checkbox(
                            checked = checkedFilter.isChecked,
                            onCheckedChange = {
                                isCheckedList.value = emptyList()
                                isCheckedList.value = clickCheckBox(checkedFilter.tags, it)
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Orange,
                                checkmarkColor = Color.White
                            )
                        )
                    }
                    HorizontalDivider(
                        modifier = Modifier.padding(top = 5.dp),
                        thickness = 2.dp,
                        color = Color.LightGray
                    )
                }
        }
    }
}
