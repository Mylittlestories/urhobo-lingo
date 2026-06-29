package com.example.urhobolingo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "progress")
data class ProgressEntity(
    @PrimaryKey val id: Int = 1,
    val xp: Int,
    val streak: Int,
    val hearts: Int,
    val hasOnboarded: Boolean
)
