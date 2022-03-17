package com.tori.schibsted.di

import android.content.Context
import com.tori.schibsted.db.RoomHelper
import com.tori.schibsted.network.IApiService
import com.tori.schibsted.preference.PreferencesHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DataManager @Inject constructor(
    val preferencesHelper: PreferencesHelper,
    val roomHelper: RoomHelper,
    val apiService: IApiService,
    @ApplicationContext context: Context
)