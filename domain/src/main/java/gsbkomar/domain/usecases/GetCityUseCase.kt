package gsbkomar.domain.usecases

import gsbkomar.domain.storage.DataCityStorage
import javax.inject.Inject

class GetCityUseCase @Inject constructor(private val dataCityStorage: DataCityStorage) {
    fun execute() : String = dataCityStorage.getCity()
}