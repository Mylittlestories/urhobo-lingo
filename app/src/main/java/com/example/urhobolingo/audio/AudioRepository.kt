package com.example.urhobolingo.audio

import android.content.Context
import kotlinx.serialization.json.Json

class AudioRepository(private val context: Context) {
    private val json = Json { ignoreUnknownKeys = true }

    fun getManifest(): AudioManifest {
        return try {
            val content = context.assets.open("audio/manifest.json")
                .bufferedReader()
                .use { it.readText() }
            json.decodeFromString(AudioManifest.serializer(), content)
        } catch (_: Exception) {
            AudioManifest()
        }
    }

    fun findPronunciationClipByText(text: String): AudioClip? {
        return getManifest().pronunciation.firstOrNull {
            it.text.equals(text, ignoreCase = true) || it.id.equals(text, ignoreCase = true)
        }
    }

    fun findGuidanceClip(id: String): AudioClip? {
        return getManifest().guidance.firstOrNull { it.id == id }
    }
}
