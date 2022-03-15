package com.tori.schibsted.presentation.main

import com.tori.schibsted.data.model.photo.PhotoResponseDto

data class PhotoSearchState (
    val isLoading: Boolean = false,
    val data: PhotoResponseDto? = null,
    val error: String = ""
)
