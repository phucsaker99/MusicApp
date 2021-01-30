package com.example.musicapp.data.model

import android.database.Cursor
import android.os.Build
import android.provider.MediaStore

data class Song(
    val id: Long,
    val size: Int,
    val title: String,
    val artist: String,
    val album: String,
    val duration: Int,
    val data: String
) {
    constructor(cursor: Cursor) : this(
        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.AlbumColumns.ALBUM_ID)),
        cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE)),
        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE)),
        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST)),
        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM)),
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) cursor.getInt(
            cursor.getColumnIndex(
                MediaStore.Audio.AudioColumns.DURATION
            )
        ) else -1,
        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA))
    )
}
