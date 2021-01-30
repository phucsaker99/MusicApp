package com.example.musicapp.data.source.local.dao

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.provider.MediaStore
import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.model.Song
import com.example.musicapp.data.source.repository.MusicDataSource
import com.example.musicapp.ui.main.MainActivity

class ResolverDataImp private constructor(private var contentResolver: ContentResolver) : ResolverDataDao {

    @SuppressLint("Recycle")
    override fun getSongList(): MutableList<Song> {
        val songList: MutableList<Song> = mutableListOf()
        val cursor = contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            null, null, null, null
        )
        cursor?.moveToFirst()
        return cursor!!.let {
            while (!it.isAfterLast) {
                songList.add(
                    Song(it)
                )
                it.moveToNext()
            }
            it.close()
            songList
        }
    }

    @SuppressLint("Recycle")
    override fun getArtistList(): MutableList<Artist> {
        val artistList: MutableList<Artist> = mutableListOf()
        val cursor = contentResolver.query(
            MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
            null, null, null, null
        )
        cursor?.moveToFirst()
        return cursor?.let {
            while (!it.isAfterLast) {
                artistList.add(
                    Artist(it)
                )
                it.moveToNext()
            }
            it.close()
            artistList
        } ?: emptyList<Artist>() as MutableList<Artist>
    }

    companion object {
        private var instance: ResolverDataImp? = null
        fun getInstance(contentResolver: ContentResolver) = instance ?: ResolverDataImp(contentResolver).also {
            instance = it
        }
    }
}
