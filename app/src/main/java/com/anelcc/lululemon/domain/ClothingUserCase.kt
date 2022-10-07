package com.anelcc.lululemon.domain

import com.anelcc.lululemon.core.Repository
import com.anelcc.lululemon.data.toDatabase
import javax.inject.Inject

class ClothingUserCase @Inject constructor(private val repository: Repository) {

    suspend fun getClothingList(): List<Clothing> {
        return  repository.getAllFromDatabase() ?: emptyList()
    }
/*
    suspend fun onInsertClothingList(list: List<Clothing>) {
        repository.insertList(list.map { it.toDatabase() })
    }*/

    suspend fun onInsertClothes(clothes :Clothing) {
        repository.insertClothes(clothes.toDatabase())
    }
/*
    suspend fun onDeletedDataBase(): List<Clothing> {
        return  repository.getAllFroDatabase() ?: emptyList()
    }*/
}
