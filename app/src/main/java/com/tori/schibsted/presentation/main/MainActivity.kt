package com.tori.schibsted.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.coroutineScope
import com.tori.schibsted.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val searchAdapter = PhotoSearchAdapter()
    private val viewModel: PhotoSearchViewModel by viewModels()

    private var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding?.root)

        initUI()
    }

    private fun initUI() {
        binding.photoSearchRecycler.apply {
            adapter = searchAdapter
        }

        binding.photoSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                s?.let {
                    Log.d("onQueryTextSubmit"," ...: "+ s )
                    viewModel.getSearchPhotos(it)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        searchAdapter.itemClickListener {
        }

        photoSearchApiCallback()
    }

    private fun photoSearchApiCallback(){
        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.photoSearchList.collect {
                if (it.isLoading) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressPhotoSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.nothingFound.visibility = View.GONE
                    binding.progressPhotoSearch.visibility = View.GONE
                    Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {
                    if (it.photos.photo.isEmpty()) {
                        binding.nothingFound.visibility = View.VISIBLE
                    }
                    binding.progressPhotoSearch.visibility = View.GONE
                    searchAdapter.setContentList(it.photos.photo.toMutableList())
                }
            }
        }
    }
}