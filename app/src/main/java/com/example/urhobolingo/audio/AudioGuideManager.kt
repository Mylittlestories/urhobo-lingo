package com.example.urhobolingo.audio

import android.content.Context

class AudioGuideManager(context: Context) {
    private val repository = AudioRepository(context)
    private val player = AudioPlayer()

    fun playGuidance(context: Context, id: String): Boolean {
        val clip = repository.findGuidanceClip(id) ?: return false
        return try {
            player.playAsset(context, clip.file)
            true
        } catch (_: Exception) {
            false
        }
    }

    fun playPronunciation(context: Context, text: String): Boolean {
        val clip = repository.findPronunciationClipByText(text) ?: return false
        return try {
            player.playAsset(context, clip.file)
            true
        } catch (_: Exception) {
            false
        }
    }

    fun stop() = player.stop()
}
