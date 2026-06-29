package com.example.urhobolingo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.urhobolingo.audio.AudioGuideManager
import com.example.urhobolingo.ui.components.AudioActionRow
import com.example.urhobolingo.viewmodel.AppViewModel

@Composable
fun LessonDetailScreen(viewModel: AppViewModel, lessonId: String, onBack: () -> Unit) {
    val state = viewModel.uiState
    val context = LocalContext.current
    val guide = remember(context) { AudioGuideManager(context) }

    DisposableEffect(guide) {
        onDispose { guide.stop() }
    }

    LaunchedEffect(lessonId) {
        if (state.selectedLesson?.id != lessonId) {
            viewModel.selectLesson(lessonId)
        }
    }

    val lesson = viewModel.uiState.selectedLesson ?: return

    if (state.quizFinished) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text("Lesson Complete", style = MaterialTheme.typography.headlineMedium)
            Text("Score: ${state.quizScore}/${lesson.quizQuestions.size}")
            Text("You earned ${lesson.xpReward} XP.")
            Button(onClick = onBack) { Text("Back to lessons") }
        }
        return
    }

    val question = lesson.quizQuestions.getOrNull(state.currentQuestionIndex) ?: return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(lesson.title, style = MaterialTheme.typography.headlineMedium)
        Text("Question ${state.currentQuestionIndex + 1} of ${lesson.quizQuestions.size}")
        AudioActionRow(
            onPlayGuide = { guide.playGuidance(context, "lesson_help") },
            onPlayPronunciation = {
                lesson.words.firstOrNull()?.let { guide.playPronunciation(context, it.urhobo) }
            }
        )
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(question.prompt, style = MaterialTheme.typography.titleLarge)
            }
        }
        question.options.forEach { option ->
            OutlinedButton(
                onClick = { viewModel.selectAnswer(option) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(option)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { viewModel.submitAnswer() },
            enabled = state.selectedAnswer != null,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Exit lesson")
        }
    }
}
