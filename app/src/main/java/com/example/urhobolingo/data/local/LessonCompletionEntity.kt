package com.example.urhobolingo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_completions")
data class LessonCompletionEntity(
    @PrimaryKey val lessonId: String,
    val completedAtEpochMs: Long
)
