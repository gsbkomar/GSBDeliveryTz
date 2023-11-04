package gsbkomar.data.implementation.repository

import gsbkomar.data.dtos.ResultsObjectDto
import gsbkomar.data.network.RetrofitFoodApiInstance
import gsbkomar.domain.models.ResultsObject
import gsbkomar.domain.repository.FoodApiRepository
import javax.inject.Inject

class FoodApiRepositoryImpl @Inject constructor() : FoodApiRepository {
    override suspend fun getFoodByCategories(category: String): ResultsObjectDto {
        return RetrofitFoodApiInstance.RetrofitInstance.getFoodList.getFoodListProvider(category = category)
    }
}