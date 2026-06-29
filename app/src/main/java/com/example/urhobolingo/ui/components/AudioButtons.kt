package com.example.urhobolingo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.Text

@Composable
fun AudioActionRow(
    onPlayGuide: (() -> Unit)? = null,
    onPlayPronunciation: (() -> Unit)? = null
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        if (onPlayGuide != null) {
            Button(onClick = onPlayGuide) { Text("Play Guide") }
        }
        if (onPlayPronunciation != null) {
            Button(onClick = onPlayPronunciation) { Text("Play Pronunciation") }
        }
    }
}
