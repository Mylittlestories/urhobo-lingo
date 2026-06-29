package com.example.urhobolingo.model

import kotlinx.serialization.Serializable

@Serializable
data class Lesson(
    val id: String,
    val title: String,
    val description: String,
    val xpReward: Int,
    val words: List<WordPair>,
    val quizQuestions: List<QuizQuestion>,
    val difficulty: String = "Beginner",
    val colorTag: String = "green"
)

@Serializable
data class WordPair(
    val english: String,
    val urhobo: String,
    val category: String = "General"
)

@Serializable
data class QuizQuestion(
    val id: String,
    val prompt: String,
    val options: List<String>,
    val answer: String,
    val explanation: String = ""
)

@Serializable
data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val unlocked: Boolean = false
)

@Serializable
data class DailyChallenge(
    val id: String,
    val title: String,
    val description: String,
    val xpReward: Int,
    val completed: Boolean = false
)

@Serializable
data class UserProgress(
    val currentStreak: Int = 3,
    val totalXp: Int = 120,
    val hearts: Int = 5,
    val completedLessons: Set<String> = emptySet(),
    val favoriteWords: Set<String> = emptySet(),
    val achievements: List<Achievement> = emptyList(),
    val hasOnboarded: Boolean = false
)
