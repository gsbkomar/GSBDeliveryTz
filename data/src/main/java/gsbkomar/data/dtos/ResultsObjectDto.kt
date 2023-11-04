package gsbkomar.data.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gsbkomar.domain.models.ResultsObject
import gsbkomar.domain.models.Search

@JsonClass(generateAdapter = true)
data class ResultsObjectDto(
    @Json(name = "searchResults")
    override val searchResults: List<SearchDto>
) : ResultsObject
