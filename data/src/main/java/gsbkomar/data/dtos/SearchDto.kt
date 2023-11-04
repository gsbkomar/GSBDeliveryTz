package gsbkomar.data.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gsbkomar.domain.models.Search

@JsonClass(generateAdapter = true)
data class SearchDto(
    @Json(name = "results")
    override val results: List<ResultsDto>
) : Search
