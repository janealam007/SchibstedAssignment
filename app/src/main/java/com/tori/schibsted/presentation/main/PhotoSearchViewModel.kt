package com.tori.schibsted.presentation.main

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.tori.schibsted.data.model.keyword.KeywordResponse
import com.tori.schibsted.R
import com.tori.schibsted.common.Resource
import com.tori.schibsted.domain.use_case.PhotoSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.io.*
import javax.inject.Inject


@HiltViewModel
class PhotoSearchViewModel @Inject constructor(
    private val photoSearchUseCase: PhotoSearchUseCase
) : ViewModel() {

    private val _photoSearchList = MutableStateFlow(PhotoSearchState())
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

    fun getSearchKeywords(context: Context): ArrayList<String> {
        val `is`: InputStream = context.resources.openRawResource(R.raw.search_suggetion)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        `is`.use { `is` ->
            val reader: Reader = BufferedReader(InputStreamReader(`is`, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }

        val searchKey: String = writer.toString()
        val keywords = Gson().fromJson(searchKey, KeywordResponse::class.java)

        val keywordList = arrayListOf<String>()

//      Here I have looping all the keywords
//      First I have checked that keyword is exist
//      If parent keyword not exist I am adding int to the keywordList list
//      If sub category keyword not exist I am Adding sub category int to the keywordList list
//      Similarly I am Adding child category into the list if not exist
        for (item in keywords.keywords!!){
            if(!keywordList.contains(item.parentName!!)){
                keywordList.add(item.parentName!!)
            }
            if(!keywordList.contains(item.subCategory!!)){
                keywordList.add(item.subCategory!!)
            }
            if(item.childCategory!!.isNotEmpty()){
                for (child in item.childCategory!!){
                    if(!keywordList.contains(child)){
                        keywordList.add(child)
                    }
                }
            }
        }

        return keywordList
    }

    fun hideSoftKeyboard(context: Context, view: View) {
        val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        manager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}