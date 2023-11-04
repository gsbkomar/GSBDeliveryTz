package gsbkomar.data.storage

import android.content.Context
import gsbkomar.data.common.Keys
import gsbkomar.data.location.prefs.CitySharedPrefs
import gsbkomar.domain.storage.DataCityStorage
import javax.inject.Inject

class DataCityStorageImpl @Inject constructor(context: Context) : DataCityStorage {

    private val citySharedPrefs: CitySharedPrefs = CitySharedPrefs.getInstance(context)

    override fun saveCity(cityName: String) {
        citySharedPrefs.saveCity(Keys.CITY_KEY, cityName)
    }

    override fun getCity(): String {
        return citySharedPrefs.getCity(Keys.CITY_KEY)
    }
}