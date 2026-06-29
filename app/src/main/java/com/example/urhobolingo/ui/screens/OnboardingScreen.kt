package com.example.urhobolingo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.urhobolingo.audio.AudioGuideManager
import com.example.urhobolingo.ui.components.AudioActionRow

@Composable
fun OnboardingScreen(onContinue: () -> Unit) {
    val context = LocalContext.current
    val guide = remember(context) { AudioGuideManager(context) }

    DisposableEffect(guide) {
        onDispose { guide.stop() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to UrhoboLingo", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Learn Urhobo through playful lessons, vocabulary practice, quizzes, streaks, and translation.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        AudioActionRow(onPlayGuide = { guide.playGuidance(context, "welcome_intro") })
        Spacer(modifier = Modifier.height(28.dp))
        Button(onClick = onContinue) {
            Text("Start Learning")
        }
    }
}
