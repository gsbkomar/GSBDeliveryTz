package gsbkomar.domain.storage

interface DataCityStorage {
    fun saveCity(cityName: String)
    fun getCity() : String
}