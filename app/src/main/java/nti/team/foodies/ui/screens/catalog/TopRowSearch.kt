package nti.team.foodies.ui.screens.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import nti.team.foodies.R
import nti.team.foodies.ui.screens.catalog.entity.SelectorTopRow


@Composable
fun TopRowSearch(
    changeTopRow: (SelectorTopRow) -> Unit,
    search: (String) -> Unit
) {

    Row{
        Box {
            Row(verticalAlignment = Alignment.CenterVertically
            ) {
                //иконка обратно
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.button_back2),
                    contentDescription = "filter",
                    modifier = Modifier.clickable {
                        changeTopRow(SelectorTopRow.ICONS)
                    }
                )

                //текст поиска
                val searchText = remember {
                    mutableStateOf("")
                }

                //поиск
                MySearchBar(searchText.value,
                    { inputText ->
                        searchText.value = inputText
                    }, {
                        search(searchText.value)
                    })
            }
        }
    }
}


