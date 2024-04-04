package nti.team.foodies.ui.screens.catalog

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import nti.team.foodies.ui.navigation.Screens
import nti.team.entity.Cart
import nti.team.entity.Product
import nti.team.foodies.ui.screens.catalog.entity.SelectorTopRow
import nti.team.foodies.ui.theme.ChangeColor
import java.util.Locale


@SuppressLint("MutableCollectionMutableState")
@Composable
fun CatalogScreen(
    navController: NavHostController,
    viewModel: CatalogViewModel = hiltViewModel(),
    updateProductList: (Product) -> Unit
) {

    //Log.d("Nik", "CatalogScreen")

    //перекрасил статус
    ChangeColor(LocalView.current, Color.White)


    //подписка на данные из апи
    val initCheckedFiltersList = viewModel.checkedFiltersStateFlow.collectAsState().value
    val productList = viewModel.productsStateFlow.collectAsState().value
    val categoryList = viewModel.categoriesStateFlow.collectAsState().value
    val error = viewModel.errorStateFlow.collectAsState().value

    //селектор верхней строки
    val selectorTopRow = remember {
        mutableStateOf(viewModel.selectorTopRow)
    }

    //ошибка
    if (error != null)
        selectorTopRow.value = SelectorTopRow.ERROR


    //список продуктов в корзине
    val productsInCart = remember {
        mutableStateListOf<Product>()
    }

    //корзина в МАП
    val cart = remember {
        mutableStateMapOf<Product, Int>()
    }
    cart.putAll(Cart.cart)

    //фильры по тэгам
    val checkedFilterList = remember {
        mutableStateOf(initCheckedFiltersList)
    }
    checkedFilterList.value = initCheckedFiltersList

    //нижнее окно с фильтром
    val bottomSheetVisible = remember { mutableStateOf(false) }

    //высота серой кнопки при открытом фильтре
    val buttonHeight = remember {
        mutableIntStateOf(0)
    }

    //кол-во фильтров
    val filterCheckSize = remember {
        mutableIntStateOf(0)
    }

    //сторока с именем для поиска
    val searchName = remember {
        mutableStateOf("")
    }

    //Log.d("Nik", "1")

    //вывод данных на экран
    Box {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 10.dp)
        ) {

            // отфильтрованный по категории продукт для вывода на экран
            val productListFilteredByChip = remember {
                mutableStateOf(productList)
            }

            // отфильтрованный по категории продукт для вывода на экран
            val productListFiltered = remember {
                mutableStateOf(productList)
            }


            //if (selectorTopRow.value == SelectorTopRow.ICONS)
            when (selectorTopRow.value) {
                SelectorTopRow.ICONS -> {
                    //отрисовка верхней строки с логотипом
                    TopRowIcons(
                        filterCheckSize = filterCheckSize.intValue,
                        { bottomSheetVisible.value = it },
                        { buttonHeight.intValue = it },
                        {
                            selectorTopRow.value = it
                            viewModel.selectorTopRow = it
                        }
                    )


                    //отрисовка чипов с категориями
                    if (categoryList != null && productList != null) {

                        if (viewModel.saverChipPos == null)
                            viewModel.saverChipPos = categoryList.first().id ?: 0

                        CreateChip(
                            chipItemList = categoryList,
                            selectCategory_id = viewModel.saverChipPos ?: 0
                        ) { id ->
                            viewModel.saverChipPos = id
                            //фильтрование продуктов по категории
                            productListFilteredByChip.value = productList.filter { product ->
                                (product.category_id == id)
                            }.toMutableList()
                        }
                    }
                }

                SelectorTopRow.SEARCH -> {
                    //отрисовка верхней строки для поиска
                    TopRowSearch(
                        {
                            selectorTopRow.value = it
                            viewModel.selectorTopRow = it
                        }, { searchText ->
                            searchName.value = searchText
                            productListFiltered.value = productList?.filter {
                                it.name?.uppercase(Locale.ROOT)?.contains(
                                    searchText.uppercase(
                                        Locale.ROOT
                                    )
                                ) ?: false
                            }
                        })
                }
                SelectorTopRow.ERROR -> {}
                SelectorTopRow.LOADING -> {}
            }
            //создал спсиок выбранных фильтров
            val filterIdList = mutableListOf<Int>()
            checkedFilterList.value?.filter { it.isChecked }?.forEach {
                filterIdList.add(it.tags.id ?: 0)
            }

            //фильтр по тэгам
            if (selectorTopRow.value == SelectorTopRow.ICONS)

                if (!checkedFilterList.value?.filter { it.isChecked }.isNullOrEmpty())
                    productListFiltered.value =
                        productListFilteredByChip.value?.filter { product ->
                            filterCheckSize.intValue = filterIdList.size
                            product.tag_ids.any { tag_ids ->
                                tag_ids in filterIdList
                            }
                        }
                else {
                    //переложил изначальный список
                    productListFiltered.value = productListFilteredByChip.value
                    filterCheckSize.intValue = 0
                }


            //Log.d("Nik", "${productListFiltered.value}")
            //засунул в бокс, чтобы отобразить кнопку корзины снизу
            Box(Modifier.clickable { bottomSheetVisible.value = false }) {

                //переменная отступа, чтобы список укоротить
                val bottomPadding = remember {
                    mutableIntStateOf(2)
                }
                if (Cart.cart.isNotEmpty())
                    bottomPadding.intValue = 60

                //отобразить отфильтрованные продукты по категории
                if (!productListFiltered.value.isNullOrEmpty()) {

                    //адаптация под экран
                    val gridCells = when (LocalConfiguration.current.orientation) {
                        android.content.res.Configuration.ORIENTATION_LANDSCAPE -> 3
                        else -> 2
                    }

                    LazyVerticalGrid(
                        GridCells.Fixed(gridCells),
                        modifier = Modifier.padding(bottom = bottomPadding.intValue.dp)
                    ) {
                        items(productListFiltered.value!!) { product ->
                            Box(modifier = Modifier.clickable {
                                updateProductList(product)
                                navController.navigate(
                                    Screens.CardFoodScreen.route,
                                )
                            }) {
                                ElementProduct(
                                    product,
                                    cart[product] ?: 0
                                ) {
                                    bottomPadding.intValue = 60
                                    productsInCart.add(it)
                                    val count = cart[it] ?: 0
                                    Cart.cart[it] = count + 1
                                    cart[it] = count + 1
                                    Cart.cart = cart
                                }
                            }
                        }
                    }

                    if (cart.isNotEmpty())
                    //отображение кнопки для перехода в корзину
                        BottomButton(navController, cart)

                } else {
                    //текст пустоты
                    EmptyText(selectorTopRow.value)
                }
            }
        }

        //серая кнопка фильтра
        Box(
            modifier = Modifier
                .height(buttonHeight.intValue.dp)
                .fillMaxSize()
        ) {
            Button(
                onClick = {
                    buttonHeight.intValue = 0
                    bottomSheetVisible.value = false
                },
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(0.5f),
                shape = RoundedCornerShape(0),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {}
        }
    }

    //открыть нижнее окно с фильтрами
    if (bottomSheetVisible.value) {
        checkedFilterList.value?.let {
            BottomSheetFilters(
                it
            ) { tags, isChecked ->
                checkedFilterList.value!!.forEach { checkedFilter ->
                    if (checkedFilter.tags == tags)
                        checkedFilter.isChecked = isChecked
                }
                //Log.d("Nik", "filterList ${filterList.value}")
                checkedFilterList.value!!
            }
        }
    }
}
















