package com.tori.schibsted.data.remote


import com.tori.schibsted.data.model.photo.PhotoResponseDto
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface SchibstedApi {
    @GET("rest/")
    suspend fun getPhotos(
        @QueryMap options: Map<String, String>
    ): PhotoResponseDto
}