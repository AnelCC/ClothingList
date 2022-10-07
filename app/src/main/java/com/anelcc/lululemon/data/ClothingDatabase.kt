package com.anelcc.lululemon.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1)
abstract class ClothingDatabase: RoomDatabase() {
    abstract fun getClothingDao(): ClothingDao
}