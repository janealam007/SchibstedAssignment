package com.tori.schibsted.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tori.schibsted.common.Resource
import com.tori.schibsted.domain.use_case.PhotoSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class PhotoSearchViewModel @Inject constructor(
    private val photoSearchUseCase: PhotoSearchUseCase
) : ViewModel() {

    private val _photoSearchList = MutableStateFlow<PhotoSearchState>(PhotoSearchState())
    val photoSearchList: StateFlow<PhotoSearchState> = _photoSearchList

    fun getSearchPhotos(search_key: String) {
        val hashmap: MutableMap<String, String> = HashMap()
        hashmap["method"] = "flickr.photos.search"
        hashmap["format"] = "json"
        hashmap["nojsoncallback"] = "1"
        hashmap["text"] = search_key
        hashmap["api_key"] = "b59eaa142fbb03d0ba6c93882fd62e30"

        photoSearchUseCase(hashmap).onEach {
            when (it) {
                is Resource.Loading -> {
                    _photoSearchList.value = PhotoSearchState(isLoading = true)
                }
                is Resource.Success -> {
                    _photoSearchList.value = PhotoSearchState(data = it.data)
                }
                is Resource.Error -> {
                    _photoSearchList.value = PhotoSearchState(error = it.message ?: "")
                }
            }
        }.launchIn(viewModelScope)
    }


}