package com.anelcc.lululemon.domain

import com.anelcc.lululemon.data.ClothingModel
import com.anelcc.lululemon.data.Entity

data class Clothing (val name:String, val date: String)


fun ClothingModel.toDomain() = Clothing(name, date)
fun Entity.toDomain() = Clothing(name, date)