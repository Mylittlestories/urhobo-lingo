package com.example.urhobolingo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_words")
data class FavoriteWordEntity(
    @PrimaryKey val english: String,
    val urhobo: String,
    val category: String
)
