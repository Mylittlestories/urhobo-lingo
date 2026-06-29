package com.example.urhobolingo.audio

import android.content.Context
import android.media.MediaPlayer

class AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun playAsset(context: Context, assetPath: String) {
        stop()
        val afd = context.assets.openFd("audio/$assetPath")
        mediaPlayer = MediaPlayer().apply {
            setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
            prepare()
            start()
        }
    }

    fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
