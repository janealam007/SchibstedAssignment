package com.tori.schibsted.domain.repository

import com.tori.schibsted.data.model.photo.PhotoResponseDto

interface PhotoSearchRepository {
    suspend fun getPhotoSearch(hash_map: MutableMap<String, String>): PhotoResponseDto
}