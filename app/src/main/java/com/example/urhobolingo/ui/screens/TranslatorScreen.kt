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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.urhobolingo.viewmodel.AppViewModel

@Composable
fun TranslatorScreen(viewModel: AppViewModel) {
    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("English ↔ Urhobo Translator", style = MaterialTheme.typography.headlineMedium)
        Text("Type a word or phrase in English or Urhobo.")
        OutlinedTextField(
            value = state.translatorInput,
            onValueChange = viewModel::updateTranslatorInput,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter text") }
        )
        Button(onClick = { viewModel.runTranslation() }) {
            Text("Translate")
        }
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Result", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(state.translatorOutput.ifBlank { "Your translation will appear here." })
            }
        }
        Text(
            "Note: this MVP uses a seed offline dictionary. Replace it with verified Urhobo language data or an API for production use.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}
