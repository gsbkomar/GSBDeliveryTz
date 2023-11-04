package gsbkomar.domain.repository

import gsbkomar.domain.models.ResultsObject

interface FoodApiRepository {
    suspend fun getFoodByCategories(category: String) : ResultsObject
}