package com.example.urhobolingo.data

import android.content.Context
import com.example.urhobolingo.model.DailyChallenge
import com.example.urhobolingo.model.Lesson
import com.example.urhobolingo.model.UserProgress
import com.example.urhobolingo.model.WordPair

class AppRepository(context: Context) {
    private val prefs = context.getSharedPreferences("urhobo_lingo_prefs", Context.MODE_PRIVATE)

    fun getLessons(): List<Lesson> = SeedData.lessons
    fun getChallenges(): List<DailyChallenge> = SeedData.dailyChallenges
    fun getDictionary(): List<WordPair> = SeedData.dictionary
    fun getLessonById(id: String): Lesson? = SeedData.lessons.firstOrNull { it.id == id }

    fun getProgress(): UserProgress {
        val xp = prefs.getInt("xp", 120)
        val streak = prefs.getInt("streak", 3)
        val hearts = prefs.getInt("hearts", 5)
        val completed = prefs.getStringSet("completed_lessons", emptySet()) ?: emptySet()
        val favorites = prefs.getStringSet("favorites", emptySet()) ?: emptySet()
        val hasOnboarded = prefs.getBoolean("has_onboarded", false)
        return UserProgress(
            currentStreak = streak,
            totalXp = xp,
            hearts = hearts,
            completedLessons = completed,
            favoriteWords = favorites,
            hasOnboarded = hasOnboarded,
            achievements = SeedData.achievements.map { achievement ->
                when (achievement.id) {
                    "first_lesson" -> achievement.copy(unlocked = completed.isNotEmpty())
                    "streak_3" -> achievement.copy(unlocked = streak >= 3)
                    "xp_100" -> achievement.copy(unlocked = xp >= 100)
                    else -> achievement
                }
            }
        )
    }

    fun completeLesson(lessonId: String, xpReward: Int) {
        val currentCompleted = prefs.getStringSet("completed_lessons", emptySet())?.toMutableSet() ?: mutableSetOf()
        if (currentCompleted.add(lessonId)) {
            val currentXp = prefs.getInt("xp", 120)
            prefs.edit()
                .putStringSet("completed_lessons", currentCompleted)
                .putInt("xp", currentXp + xpReward)
                .apply()
        }
    }

    fun toggleFavorite(value: String) {
        val favorites = prefs.getStringSet("favorites", emptySet())?.toMutableSet() ?: mutableSetOf()
        if (favorites.contains(value)) favorites.remove(value) else favorites.add(value)
        prefs.edit().putStringSet("favorites", favorites).apply()
    }

    fun translate(input: String): String {
        val normalized = input.trim()
        val direct = SeedData.dictionary.firstOrNull { it.english.equals(normalized, ignoreCase = true) }
        if (direct != null) return direct.urhobo

        val reverse = SeedData.dictionary.firstOrNull { it.urhobo.equals(normalized, ignoreCase = true) }
        if (reverse != null) return reverse.english

        return "No translation found yet."
    }

    fun setOnboarded() {
        prefs.edit().putBoolean("has_onboarded", true).apply()
    }
}
