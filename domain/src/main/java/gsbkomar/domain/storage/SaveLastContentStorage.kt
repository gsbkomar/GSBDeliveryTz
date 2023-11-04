package gsbkomar.domain.storage

import gsbkomar.domain.models.ResultsObject

interface SaveLastContentStorage {
    fun saveLastContent(content: ResultsObject)
}