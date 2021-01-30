package com.example.musicapp.ui.notification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class NotificationApp : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelSong = NotificationChannel(
                CHANNEL_SONG_ID,
                "Song ID",
                NotificationManager.IMPORTANCE_HIGH
            )
            channelSong.description = "Current Song"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channelSong)
        }
    }

    companion object {
        const val CHANNEL_SONG_ID = "SongID"
    }
}
