package com.example.musicapp.ui.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.RemoteViews
import androidx.annotation.IdRes
import androidx.core.app.NotificationCompat
import com.example.musicapp.R
import com.example.musicapp.controller.MediaController
import com.example.musicapp.data.model.Song
import com.example.musicapp.ui.main.MainActivity

open class SongService : Service() {
    private var remoteView: RemoteViews? = null
    private var notificationReceiver: NotificationReceiver? = null

    override fun onCreate() {
        super.onCreate()
        initRemoteViews()
        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_PREV)
        intentFilter.addAction(ACTION_PLAY)
        intentFilter.addAction(ACTION_SKIP)
        intentFilter.addAction(ACTION_CLOSE)
        registerReceiver(notificationReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        MainActivity.mediaMusic?.release()
        unregisterReceiver(NotificationReceiver())
    }

    override fun onBind(intent: Intent?): IBinder? = SongServiceBinder(this)

    open fun stopService() {
        stopForeground(true)
        MainActivity.mediaMusic?.release()
        MainActivity.mediaMusic = null
        stopSelf()
    }

    fun setSongList(songList: List<Song>) {
        if (MainActivity.mediaMusic != null) {
            pushNotify(songList[MediaController.index])
        }
    }

    fun pushNotify(song: Song) {
        remoteView?.apply {
            setTextViewText(R.id.textSongNotification, song.title)
            setTextViewText(R.id.textArtistNotification, song.artist)
            if (MainActivity.mediaMusic!!.isPlaying) {
                remoteView?.setImageViewResource(R.id.buttonPauseNotification, R.drawable.ic_play)
            } else {
                remoteView?.setImageViewResource(R.id.buttonPauseNotification, R.drawable.ic_pause)
            }
        }

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(
            this, NotificationApp.CHANNEL_SONG_ID
        )
        builder.setSmallIcon(R.drawable.ic_music)
        builder.setCustomContentView(remoteView)
        builder.priority = NotificationCompat.PRIORITY_HIGH
        builder.setStyle(NotificationCompat.DecoratedCustomViewStyle())
        startForeground(1, builder.build())
    }

    private fun initRemoteViews() {
        notificationReceiver = NotificationReceiver()
        remoteView = RemoteViews(packageName, R.layout.song_notification)
        registerAction(R.id.buttonPrevNotification, ACTION_PREV)
        registerAction(R.id.buttonPauseNotification, ACTION_PLAY)
        registerAction(R.id.buttonSkipNotification, ACTION_SKIP)
        registerAction(R.id.buttonCloseNotification, ACTION_CLOSE)
    }

    private fun registerAction(@IdRes viewAction: Int, action: String) {
        val intent = Intent(action)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        remoteView?.setOnClickPendingIntent(viewAction, pendingIntent)
    }

    class SongServiceBinder(private val service: SongService) : Binder() {
        fun getService(): SongService = service
    }

    companion object {
        const val ACTION_SKIP = "action.Next"
        const val ACTION_PREV = "action.Prev"
        const val ACTION_PLAY = "action.Play"
        const val ACTION_CLOSE = "action.Close"
        fun getIntent(context: Context) = Intent(context, SongService::class.java)
        private var instance: SongService? = null
        fun getInstance() = instance ?: SongService().also {
            instance = it
        }
    }
}
