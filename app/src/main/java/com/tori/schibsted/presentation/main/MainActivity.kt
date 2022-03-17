package com.tori.schibsted.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import com.tori.schibsted.R
import com.tori.schibsted.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val searchAdapter = PhotoSearchAdapter()
    private val photoSearchViewModel: PhotoSearchViewModel by viewModels()

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        photoSearchApiCallback()
        initUI()
    }

    private fun initUI() {
        //Photo adapter recycle view
        binding!!.photoSearchRecycler.apply {
            adapter = searchAdapter
        }

        // Auto suggestions adapter
        val keywordAdapter = ArrayAdapter(
            this@MainActivity,
            R.layout.simple_dropdown_item, photoSearchViewModel.getSearchKeywords(this@MainActivity)
        )
        binding!!.autoSuggestionTextview.setAdapter(keywordAdapter)

        //Auto suggestion item selected
        binding!!.autoSuggestionTextview.onItemClickListener =
            OnItemClickListener { adapterView, view, itemIndex, id ->
                //Hide Keyboard
                photoSearchViewModel.hideSoftKeyboard(this@MainActivity, binding!!.autoSuggestionTextview)
                val queryItem = adapterView.getItemAtPosition(itemIndex) as String
                photoSearchViewModel.getSearchPhotos(queryItem)
            }
    }

    // View Model data change listener for api
    private fun photoSearchApiCallback(){
        lifecycle.coroutineScope.launchWhenCreated {
            photoSearchViewModel.photoSearchList.collect { it ->
                if (it.isLoading) {
                    binding!!.nothingFound.visibility = View.GONE
                    binding!!.progressPhotoSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding!!.nothingFound.visibility = View.GONE
                    binding!!.progressPhotoSearch.visibility = View.GONE
                    Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {
                    if (it.photos.photo.isEmpty()) {
                        binding!!.nothingFound.visibility = View.VISIBLE
                    }
                    binding!!.progressPhotoSearch.visibility = View.GONE
                    searchAdapter.setContentList(it.photos.photo.toMutableList())
                }
            }
        }
    }

    //Before leaving the activity emptying the binding
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}