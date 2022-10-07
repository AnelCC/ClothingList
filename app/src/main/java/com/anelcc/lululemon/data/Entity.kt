package com.anelcc.lululemon.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anelcc.lululemon.domain.Clothing

@Entity(tableName = "clothing_table")
data class Entity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "date") val date: String)

fun Clothing.toDatabase() = Entity(name = name, date =  date)