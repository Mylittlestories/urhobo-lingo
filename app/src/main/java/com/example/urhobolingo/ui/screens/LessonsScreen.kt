package com.example.urhobolingo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.urhobolingo.ui.components.LessonCard
import com.example.urhobolingo.viewmodel.AppViewModel

@Composable
fun LessonsScreen(viewModel: AppViewModel, onOpenLesson: (String) -> Unit) {
    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Lessons", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(state.lessons) { lesson ->
                LessonCard(
                    lesson = lesson,
                    completed = state.progress.completedLessons.contains(lesson.id),
                    onPrimaryAction = {
                        viewModel.selectLesson(lesson.id)
                        onOpenLesson(lesson.id)
                    }
                )
            }
        }
    }
}
