package nti.team.foodies.ui.screens.catalog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nti.team.entity.Categories
import nti.team.entity.Product
import nti.team.entity.ResultFromApi
import nti.team.entity.Tags
import nti.team.entity.UseCaseApiInterface
import nti.team.foodies.ui.screens.catalog.entity.CheckedFilters
import nti.team.foodies.ui.screens.catalog.entity.SelectorTopRow
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val useCaseApi: UseCaseApiInterface
) : ViewModel() {

    init {
        getCategories()
        getTags()
        getProducts()
    }

    //ошибки
    private val _errorStateFlow = MutableStateFlow<String?>(null)
    val errorStateFlow = _errorStateFlow

    //категории
    private val _categoriesStateFlow = MutableStateFlow<List<Categories>?>(null)
    val categoriesStateFlow = _categoriesStateFlow.asStateFlow()

    var saverChipPos: Int? = null

    var selectorTopRow = SelectorTopRow.ICONS

    private val _selectorStateFlow = MutableStateFlow<SelectorTopRow>(SelectorTopRow.LOADING)
    val selectorStateFlow = _selectorStateFlow.asStateFlow()


    //получить категории из апи
    private fun getCategories() {
        //корутина для обращения к апи
        viewModelScope.launch {
            //проверить на приход ошибки и положить в соотв. переменную
            when (val result = useCaseApi.getCategories()) {
                is ResultFromApi.Success<*> -> {
                    _categoriesStateFlow.value =
                        result.successData as List<Categories>
                    _selectorStateFlow.value = SelectorTopRow.ICONS
                }
                //ошибка
                is ResultFromApi.Error<*> -> {
                    _errorStateFlow.value = result.ErrorMessage as String
                }
            }
        }
    }

    //тэги
    private val _checkedFiltersStateFlow = MutableStateFlow<List<CheckedFilters>?>(null)
    val checkedFiltersStateFlow = _checkedFiltersStateFlow.asStateFlow()

    //получить тэги из апи
    private fun getTags() {
        //корутина для обращения к апи


        viewModelScope.launch {
            //проверить на приход ошибки и положить в соотв. переменную
            when (val result = useCaseApi.getTags()) {
                is ResultFromApi.Success<*> -> {
                    val tagsList = result.successData as List<Tags>
                    val checkedFilters = mutableListOf<CheckedFilters>()
                    tagsList.forEach {
                        checkedFilters.add(CheckedFilters(it))
                    }
                    _checkedFiltersStateFlow.value = checkedFilters
                    _selectorStateFlow.value = SelectorTopRow.ICONS
                }
                //ошибка
                is ResultFromApi.Error<*> -> {
                    _errorStateFlow.value = result.ErrorMessage as String
                }
            }
        }
    }

    //продукция
    private val _productStateFlow = MutableStateFlow<List<Product>?>(null)
    val productsStateFlow = _productStateFlow.asStateFlow()

    //получить продукцию из апи
    private fun getProducts() {

        //корутина для обращения к апи
        viewModelScope.launch {
            //проверить на приход ошибки и положить в соотв. переменную
            when (val test = useCaseApi.getProducts()) {
                is ResultFromApi.Success<*> -> {
                    _productStateFlow.value = test.successData as List<Product>
                    _selectorStateFlow.value = SelectorTopRow.ICONS
                }

                //ошибка
                is ResultFromApi.Error<*> -> {
                    _errorStateFlow.value = test.ErrorMessage as String
                }
            }
        }
    }
}