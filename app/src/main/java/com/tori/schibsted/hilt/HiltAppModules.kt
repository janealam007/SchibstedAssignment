package com.tori.schibsted.hilt

import com.tori.schibsted.common.Constants
import com.tori.schibsted.data.remote.SchibstedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object HiltAppModules {
    @Provides
    @Singleton
    fun provideHiltSearchApi(): SchibstedApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(SchibstedApi::class.java)
    }

}