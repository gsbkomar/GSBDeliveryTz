package gsbkomar.data.mappers

import gsbkomar.data.dtos.ResultsDto
import gsbkomar.data.dtos.ResultsObjectDto
import gsbkomar.data.dtos.SearchDto
import gsbkomar.domain.models.Results
import gsbkomar.domain.models.ResultsObject
import gsbkomar.domain.models.Search

fun Results.toDto() = ResultsDto(
    name = name,
    image = image,
    content = content
)

fun Search.toDto() = SearchDto(
    results = results.map { it.toDto() }
)

fun ResultsObject.toDto() = ResultsObjectDto(
    searchResults = searchResults.map { it.toDto() }
)

