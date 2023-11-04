package gsbkomar.domain.usecases

import gsbkomar.domain.models.ResultsObject
import gsbkomar.domain.repository.FoodApiRepository
import javax.inject.Inject

class GetFoodByCategoryUseCase @Inject constructor(private val foodApiRepository: FoodApiRepository) {
    suspend fun execute(category: String) : ResultsObject = foodApiRepository.getFoodByCategories(category)
}