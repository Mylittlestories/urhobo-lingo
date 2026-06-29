package com.example.urhobolingo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.urhobolingo.audio.AudioPlayer
import com.example.urhobolingo.audio.AudioRepository
import com.example.urhobolingo.model.WordPair
import com.example.urhobolingo.tts.SpeechHelper
import com.example.urhobolingo.viewmodel.AppViewModel

@Composable
fun VocabularyScreen(viewModel: AppViewModel) {
    val state = viewModel.uiState
    val context = LocalContext.current
    val speech = remember(context) { SpeechHelper(context) }
    val audioPlayer = remember { AudioPlayer() }
    val audioRepository = remember(context) { AudioRepository(context) }

    DisposableEffect(speech, audioPlayer) {
        onDispose {
            speech.shutdown()
            audioPlayer.stop()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Vocabulary", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(state.dictionary) { word ->
                WordCard(
                    word = word,
                    isFavorite = state.progress.favoriteWords.contains(word.english),
                    onFavorite = { viewModel.toggleFavorite(word) },
                    onSpeak = {
                        val clip = audioRepository.findPronunciationClipByText(word.urhobo)
                        if (clip != null) {
                            audioPlayer.playAsset(context, clip.file)
                        } else {
                            speech.speak(word.english)
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun WordCard(word: WordPair, isFavorite: Boolean, onFavorite: () -> Unit, onSpeak: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(word.english, style = MaterialTheme.typography.titleMedium)
                Text(word.urhobo, style = MaterialTheme.typography.bodyLarge)
                Text(word.category, style = MaterialTheme.typography.bodySmall)
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onFavorite) {
                    Text(if (isFavorite) "Unsave" else "Save")
                }
                Button(onClick = onSpeak) {
                    Text("Play Audio")
                }
            }
        }
    }
}
