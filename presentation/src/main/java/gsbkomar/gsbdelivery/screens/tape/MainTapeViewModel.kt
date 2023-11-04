package gsbkomar.gsbdelivery.screens.tape

import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import gsbkomar.data.dtos.ResultsObjectDto
import gsbkomar.data.implementation.repository.FoodApiRepositoryImpl
import gsbkomar.data.mappers.toDto
import gsbkomar.data.storage.DataCityStorageImpl
import gsbkomar.domain.models.ResultsObject
import gsbkomar.domain.storage.DataCityStorage
import gsbkomar.domain.usecases.GetCityUseCase
import gsbkomar.domain.usecases.GetFoodByCategoryUseCase
import gsbkomar.gsbdelivery.MainActivity
import gsbkomar.gsbdelivery.R
import gsbkomar.gsbdelivery.common.State
import gsbkomar.gsbdelivery.screens.tape.models.banner.Banner
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

class MainTapeViewModel @Inject constructor(
    private val foodApiRepositoryImpl: FoodApiRepositoryImpl,
): ViewModel() {

    private var _state = MutableStateFlow<State>(State.Loading)
    var state = _state.asStateFlow()

    private var _error = Channel<String>()
    var error = _error.receiveAsFlow()

    private val getFoodByCategoryUseCase = GetFoodByCategoryUseCase(foodApiRepositoryImpl)
    private lateinit var getCityUseCase: GetCityUseCase

    suspend fun getFoodListByCategory(category: String) : ResultsObjectDto {
        _state.value = State.Loading
        val result = getFoodByCategoryUseCase.execute(category).toDto()
        _state.value = State.Success
        return result
    }

    fun getCityData(dataCityStorage: DataCityStorage) : String {
        _state.value = State.Loading
        getCityUseCase = GetCityUseCase(dataCityStorage)
        val city = getCityUseCase.execute()
        _state.value = State.Success
        return city
    }
}