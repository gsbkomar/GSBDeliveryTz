package gsbkomar.domain.usecases

import gsbkomar.domain.storage.DataCityStorage
import javax.inject.Inject

class SaveCityUseCase @Inject constructor(private val saveCityStorage: DataCityStorage) {
    fun execute(cityName: String) = saveCityStorage.saveCity(cityName)
}