package com.tori.schibsted.network.data.photo

data class PhotosDto(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<PhotoDto>,
    val total: Int
)