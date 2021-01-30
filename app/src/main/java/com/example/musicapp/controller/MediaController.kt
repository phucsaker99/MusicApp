package com.example.musicapp.controller

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import com.example.musicapp.data.model.Song

open class MediaController(
    private val songs: List<Song>,
    private val context: Context
) : OnCompletionListener {
    private var player: MediaPlayer? = null
    val isPlaying: Boolean = player?.isPlaying != null

    open fun create(index: Int) {
        release()
        Companion.index = index
        val uri = Uri.parse(songs[index].data)
        player
        player = MediaPlayer.create(context, uri)
        start()
        player?.setOnCompletionListener(this)
    }

    open fun start() = player?.start()

    open fun pause() = player?.pause()

    fun release() = player?.release()

    fun stop() = player?.stop()

    fun random(): Int {
        val size = songs.size
        index = (Math.random() * size).toInt()
        return index
    }

    fun loop(isLooping: Boolean) = player?.let { it.isLooping = isLooping }

    fun seek(position: Int) = player?.seekTo(position)

    open fun change(value: Int) {
        index += value
        if (index < 0) {
            index = songs.size - 1
        } else if (index >= songs.size) {
            index = 0
        }
        create(index)
    }

    open fun endSong(): Boolean = player?.duration == player?.currentPosition

    override fun onCompletion(mp: MediaPlayer) = change(1)

    companion object {
        var index = 0
        private var instance: MediaController? = null
        fun getInstance(songs: List<Song>, context: Context): MediaController =
            instance ?: MediaController(songs, context).also {
                instance = it
            }
    }
}
