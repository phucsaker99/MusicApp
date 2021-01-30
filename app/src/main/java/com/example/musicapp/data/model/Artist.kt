package com.example.musicapp.data.model

import android.database.Cursor
import android.provider.MediaStore

data class Artist(
    val id: Long,
    val artist: String,
    val countAlbum: Int,
    val countSong: Int
) {
    constructor(cursor: Cursor) : this(
        cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Artists._ID)),
        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST)),
        cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS)),
        cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS))
    )
}
