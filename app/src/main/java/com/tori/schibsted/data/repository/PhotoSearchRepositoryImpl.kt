package com.tori.schibsted.data.repository

import com.tori.schibsted.data.model.photo.PhotoResponseDto
import com.tori.schibsted.data.remote.SchibstedApi
import com.tori.schibsted.domain.repository.PhotoSearchRepository


class PhotoSearchRepositoryImpl(private val photoSearchAPI: SchibstedApi) : PhotoSearchRepository {
    override suspend fun getPhotoSearch(hash_map: MutableMap<String, String>): PhotoResponseDto {
        return photoSearchAPI.getPhotos(hash_map)
    }
}