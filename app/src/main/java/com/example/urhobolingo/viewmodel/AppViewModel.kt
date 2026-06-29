package com.example.urhobolingo.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.urhobolingo.data.AppRepository
import com.example.urhobolingo.model.DailyChallenge
import com.example.urhobolingo.model.Lesson
import com.example.urhobolingo.model.QuizQuestion
import com.example.urhobolingo.model.UserProgress
import com.example.urhobolingo.model.WordPair

data class UiState(
    val lessons: List<Lesson> = emptyList(),
    val challenges: List<DailyChallenge> = emptyList(),
    val dictionary: List<WordPair> = emptyList(),
    val progress: UserProgress = UserProgress(),
    val translatorInput: String = "",
    val translatorOutput: String = "",
    val selectedLesson: Lesson? = null,
    val currentQuestionIndex: Int = 0,
    val selectedAnswer: String? = null,
    val quizScore: Int = 0,
    val quizFinished: Boolean = false
)

class AppViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AppRepository(application)

    var uiState by mutableStateOf(loadState())
        private set

    private fun loadState() = UiState(
        lessons = repository.getLessons(),
        challenges = repository.getChallenges(),
        dictionary = repository.getDictionary(),
        progress = repository.getProgress()
    )

    fun completeLesson(lesson: Lesson) {
        repository.completeLesson(lesson.id, lesson.xpReward)
        refreshProgressOnly()
    }

    fun toggleFavorite(wordPair: WordPair) {
        repository.toggleFavorite(wordPair.english)
        refreshProgressOnly()
    }

    fun updateTranslatorInput(value: String) {
        uiState = uiState.copy(translatorInput = value)
    }

    fun runTranslation() {
        uiState = uiState.copy(translatorOutput = repository.translate(uiState.translatorInput))
    }

    fun finishOnboarding() {
        repository.setOnboarded()
        refreshProgressOnly()
    }

    fun selectLesson(lessonId: String) {
        val lesson = repository.getLessonById(lessonId) ?: return
        uiState = uiState.copy(
            selectedLesson = lesson,
            currentQuestionIndex = 0,
            selectedAnswer = null,
            quizScore = 0,
            quizFinished = false
        )
    }

    fun selectAnswer(answer: String) {
        uiState = uiState.copy(selectedAnswer = answer)
    }

    fun submitAnswer() {
        val lesson = uiState.selectedLesson ?: return
        val question: QuizQuestion = lesson.quizQuestions[uiState.currentQuestionIndex]
        val add = if (uiState.selectedAnswer == question.answer) 1 else 0
        val nextIndex = uiState.currentQuestionIndex + 1
        if (nextIndex >= lesson.quizQuestions.size) {
            repository.completeLesson(lesson.id, lesson.xpReward)
            uiState = uiState.copy(
                quizScore = uiState.quizScore + add,
                quizFinished = true,
                progress = repository.getProgress()
            )
        } else {
            uiState = uiState.copy(
                currentQuestionIndex = nextIndex,
                selectedAnswer = null,
                quizScore = uiState.quizScore + add
            )
        }
    }

    fun exitLesson() {
        uiState = uiState.copy(
            selectedLesson = null,
            currentQuestionIndex = 0,
            selectedAnswer = null,
            quizScore = 0,
            quizFinished = false
        )
    }

    private fun refreshProgressOnly() {
        uiState = uiState.copy(progress = repository.getProgress())
    }
}
