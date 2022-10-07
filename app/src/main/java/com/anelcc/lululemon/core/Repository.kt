package com.anelcc.lululemon.core

import com.anelcc.lululemon.data.ClothingDao
import com.anelcc.lululemon.data.Entity
import com.anelcc.lululemon.domain.Clothing
import com.anelcc.lululemon.domain.toDomain
import javax.inject.Inject

class Repository @Inject constructor(private val clothingDao: ClothingDao){

    suspend fun getAllFromDatabase() : List<Clothing> {
        val response: List<Entity> = clothingDao.getAllClothing()
        return response.map { it.toDomain() }
    }

    suspend fun insertList(clothingList :List<Entity>){
        clothingDao.insertAll(clothingList)
    }

    suspend fun insertClothes(clothes: Entity){
        clothingDao.insertAClothes(clothes)
    }

    suspend fun clearList(){
        clothingDao.deleteAll()
    }
}