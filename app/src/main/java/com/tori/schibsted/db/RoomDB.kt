package com.tori.schibsted.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tori.schibsted.db.dao.ImageConfigDao
import com.tori.schibsted.db.entity.ImagesConfig


@Database(entities = [ImagesConfig::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {

    //abstract fun imageConfigDao(): ImageConfigDao
}