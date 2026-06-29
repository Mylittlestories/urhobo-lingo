package com.example.urhobolingo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.urhobolingo.ui.components.PathNode
import com.example.urhobolingo.viewmodel.AppViewModel

@Composable
fun PathScreen(viewModel: AppViewModel) {
    val state = viewModel.uiState
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Learning Path", style = MaterialTheme.typography.headlineMedium)
        LazyColumn(
            modifier = Modifier.padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.lessons) { lesson ->
                PathNode(
                    title = lesson.title,
                    completed = state.progress.completedLessons.contains(lesson.id),
                    onClick = { viewModel.selectLesson(lesson.id) }
                )
            }
        }
    }
}
