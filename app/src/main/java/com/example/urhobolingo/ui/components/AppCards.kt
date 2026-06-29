package com.example.urhobolingo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.urhobolingo.model.DailyChallenge
import com.example.urhobolingo.model.Lesson

@Composable
fun ProgressCard(streak: Int, xp: Int, hearts: Int) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Column {
                Text("Daily Streak", color = Color.White)
                Text("$streak days", color = Color.White, style = MaterialTheme.typography.headlineSmall)
            }
            Column {
                Text("XP", color = Color.White)
                Text("$xp", color = Color.White, style = MaterialTheme.typography.headlineSmall)
            }
            Column {
                Text("Hearts", color = Color.White)
                Text("$hearts", color = Color.White, style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}

@Composable
fun LessonCard(lesson: Lesson, completed: Boolean, onPrimaryAction: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(lesson.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(lesson.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Reward: ${lesson.xpReward} XP • ${lesson.difficulty}")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = onPrimaryAction) {
                Text(if (completed) "Review Lesson" else "Start Lesson")
            }
        }
    }
}

@Composable
fun ChallengeRow(challenges: List<DailyChallenge>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(challenges.size) { index ->
            val challenge = challenges[index]
            Card(
                modifier = Modifier.width(220.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(challenge.title, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(challenge.description)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("+${challenge.xpReward} XP")
                }
            }
        }
    }
}

@Composable
fun StatPill(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f), RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    )
}

@Composable
fun PathNode(title: String, completed: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = if (completed) MaterialTheme.colorScheme.secondary.copy(alpha = 0.25f)
            else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(title, style = MaterialTheme.typography.titleMedium)
            Text(if (completed) "✅" else "▶")
        }
    }
}
