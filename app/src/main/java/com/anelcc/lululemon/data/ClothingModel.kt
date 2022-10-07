package com.anelcc.lululemon.data

import java.util.*

class ClothingModel(val name: String, val date: String)

//(FULL_TIME, PART_TIME, CONTRACTOR)
enum class ClothingSortType(val value: String) {
    ALPHA("ALPHA"),
    TIME("TIME"),
}
