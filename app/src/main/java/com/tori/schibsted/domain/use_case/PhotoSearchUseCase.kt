package com.tori.schibsted.domain.use_case

import android.util.Log
import com.tori.schibsted.common.Resource
import com.tori.schibsted.data.model.photo.PhotoResponseDto
import com.tori.schibsted.domain.repository.PhotoSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PhotoSearchUseCase @Inject constructor(private val repository: PhotoSearchRepository) {

    operator fun invoke(hash_map: MutableMap<String, String>): Flow<Resource<PhotoResponseDto>> = flow {
        try {
            emit(Resource.Loading())
            val data = repository.getPhotoSearch(hash_map)
            emit(Resource.Success(data = data))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An Unknown error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Check Connectivity"))
        } catch (e: Exception) {
        }
    }
}