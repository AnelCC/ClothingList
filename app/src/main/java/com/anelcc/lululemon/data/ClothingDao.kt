package com.anelcc.lululemon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClothingDao {

    @Query("SELECT * FROM CLOTHING_TABLE ORDER BY name ASC")
    suspend fun getAllClothing(): List<Entity>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clothing:  List<Entity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAClothes( lothes: Entity?)


    @Query("DELETE FROM CLOTHING_TABLE")
    suspend fun deleteAll()
}