package com.example.urhobolingo.audio

import kotlinx.serialization.Serializable

@Serializable
data class AudioManifest(
    val guidance: List<AudioClip> = emptyList(),
    val pronunciation: List<AudioClip> = emptyList()
)

@Serializable
data class AudioClip(
    val id: String,
    val title: String? = null,
    val text: String? = null,
    val file: String,
    val speaker: String,
    val language: String
)
