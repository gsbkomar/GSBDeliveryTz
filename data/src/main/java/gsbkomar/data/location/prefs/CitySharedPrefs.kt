package gsbkomar.data.location.prefs

import android.content.Context
import android.content.SharedPreferences
import gsbkomar.data.common.Keys

class CitySharedPrefs private constructor(context: Context) {
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun saveCity(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getCity(key: String): String {
        return sharedPreferences.getString(key, "") as String
    }

    companion object {
        private const val PREF_NAME = Keys.PREFS_CITY_KEY
        private var instance: CitySharedPrefs? = null

        fun getInstance(context: Context): CitySharedPrefs {
            if (instance == null) {
                instance = CitySharedPrefs(context)
            }
            return instance!!
        }
    }
}