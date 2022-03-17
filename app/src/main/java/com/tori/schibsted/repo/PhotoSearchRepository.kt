package com.tori.schibsted.repo

import com.tori.schibsted.BuildConfig
import com.tori.schibsted.network.data.photo.PhotoResponseDto
import com.tori.schibsted.di.DataManager
import com.tori.schibsted.network.convertData
import javax.inject.Inject

class PhotoSearchRepository @Inject constructor(
    val dataManager: DataManager
) : IPhotoSearchRepository {
    override suspend fun getPhotoSearch(
        method: String,
        format: String,
        nojsoncallback: String,
        search_key: String
    ): PhotoResponseDto {
        val hashMap = HashMap<String, String>()
        hashMap["method"] = method
        hashMap["format"] = format
        hashMap["nojsoncallback"] = nojsoncallback
        hashMap["text"] = search_key
        hashMap["api_key"] = BuildConfig.API_KEY

        return dataManager
            .apiService
            .getRequest("rest/", hashMap)
            .convertData(PhotoResponseDto::class) as PhotoResponseDto
    }
}