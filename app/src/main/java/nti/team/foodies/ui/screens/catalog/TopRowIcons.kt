package nti.team.foodies.ui.screens.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import nti.team.foodies.R
import nti.team.foodies.ui.screens.catalog.entity.SelectorTopRow

//иконки
@Composable
fun TopRowIcons(
    filterCheckSize: Int,
    bottomSheetVisible: (Boolean) -> Unit,
    buttonHeight: (Int) -> Unit,
    changeTopRow: (SelectorTopRow) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.height(35.dp)) {
            Row(modifier = Modifier.fillMaxHeight()) {
                //иконка фильтра
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.button_filter),
                    contentDescription = "filter",
                    modifier = Modifier
                        .fillMaxHeight()
                        .clickable {
                            bottomSheetVisible(true)
                            buttonHeight(500)
                        },
                    alignment = Alignment.BottomStart
                )
                //отображение кол-ва фильтров в кружке
                Box {
                    if (filterCheckSize > 0) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.indicator),
                            contentDescription = "filter",
                            modifier = Modifier
                                .height(20.dp)
                                .fillMaxHeight()
                                .padding(top = 2.dp, start = 1.dp),
                        )
                        Text(
                            text = filterCheckSize.toString(),
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 5.dp)
                        )
                    }
                }
            }
        }
        //логотип
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.logo3),
            contentDescription = "logo"
        )
        //поиск
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.search),
            contentDescription = "search",
            modifier = Modifier.clickable {
                changeTopRow(SelectorTopRow.SEARCH)
                //navController.navigate(Screens.SearchScreen.route)
            }
        )
    }

}