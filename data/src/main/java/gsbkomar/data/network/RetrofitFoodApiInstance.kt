package gsbkomar.data.network


import gsbkomar.data.dtos.ResultsObjectDto
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Inject

class RetrofitFoodApiInstance @Inject constructor() {

    object RetrofitInstance {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val getFoodList: GetFoodListProvider = retrofit.create(GetFoodListProvider::class.java)
    }

    interface GetFoodListProvider {
        @Headers(
            "Accept: application/json",
            "Content-type: application/json",
        )
        @GET("food/search?")
        suspend fun getFoodListProvider(
            @Query("query") category: String,
            @Query("apiKey") key: String = API_KEY
        ): ResultsObjectDto
    }

    companion object {
        const val BASE_URL = "https://api.spoonacular.com/"
        const val API_KEY = "be39c0f96e974031872c3b1a6ba68c6b"
    }
}