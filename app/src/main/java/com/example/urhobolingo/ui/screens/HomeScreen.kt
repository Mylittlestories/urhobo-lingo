package com.example.urhobolingo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.urhobolingo.audio.AudioGuideManager
import com.example.urhobolingo.ui.components.AudioActionRow
import com.example.urhobolingo.ui.components.ChallengeRow
import com.example.urhobolingo.ui.components.ProgressCard
import com.example.urhobolingo.ui.components.StatPill
import com.example.urhobolingo.viewmodel.AppViewModel

@Composable
fun HomeScreen(viewModel: AppViewModel) {
    val state = viewModel.uiState
    val context = LocalContext.current
    val guide = remember(context) { AudioGuideManager(context) }

    DisposableEffect(guide) {
        onDispose { guide.stop() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Learn Urhobo the playful way", style = MaterialTheme.typography.headlineMedium)
        Text("Short lessons, streaks, quizzes, saved words, and translation support.")
        AudioActionRow(onPlayGuide = { guide.playGuidance(context, "welcome_intro") })
        ProgressCard(
            streak = state.progress.currentStreak,
            xp = state.progress.totalXp,
            hearts = state.progress.hearts
        )

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Quick Stats", style = MaterialTheme.typography.titleLarge)
            StatPill("Lessons completed: ${state.progress.completedLessons.size}")
            StatPill("Favorite words: ${state.progress.favoriteWords.size}")
            StatPill("Achievements unlocked: ${state.progress.achievements.count { it.unlocked }}")
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text("Daily Challenges", style = MaterialTheme.typography.titleLarge)
            ChallengeRow(state.challenges)
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            Text("Achievements", style = MaterialTheme.typography.titleLarge)
            state.progress.achievements.forEach {
                Text("${if (it.unlocked) "✅" else "🔒"} ${it.title} — ${it.description}")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}
