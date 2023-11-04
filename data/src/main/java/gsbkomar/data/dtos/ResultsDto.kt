package gsbkomar.data.dtos

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import gsbkomar.domain.models.Results

@JsonClass(generateAdapter = true)
data class ResultsDto(
    @Json(name = "name")
    override val name: String,
    @Json(name = "image")
    override val image: String,
    @Json(name = "content")
    override val content: String?
) : Results
