package com.example.urhobolingo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {
    @Query("SELECT * FROM progress WHERE id = 1")
    suspend fun getProgress(): ProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProgress(progress: ProgressEntity)

    @Query("SELECT * FROM favorite_words")
    suspend fun getFavorites(): List<FavoriteWordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(word: FavoriteWordEntity)

    @Query("DELETE FROM favorite_words WHERE english = :english")
    suspend fun deleteFavorite(english: String)

    @Query("SELECT * FROM lesson_completions")
    suspend fun getCompletedLessons(): List<LessonCompletionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun markLessonCompleted(entity: LessonCompletionEntity)
}
