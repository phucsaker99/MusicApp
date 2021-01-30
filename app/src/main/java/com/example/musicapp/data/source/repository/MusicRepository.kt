package com.example.musicapp.data.source.repository

import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.model.Song
import com.example.musicapp.data.source.local.util.OnDataLocalCallback

class MusicRepository(
    private val local: MusicDataSource.Local
) : MusicDataSource.Local {

    override fun getSongList(callback: OnDataLocalCallback<List<Song>>) {
        local.getSongList(callback)
    }

    override fun getArtistList(callback: OnDataLocalCallback<List<Artist>>) {
        local.getArtistList(callback)
    }

    companion object {
        private var instance: MusicRepository? = null
        fun getInstance(local: MusicDataSource.Local) =
            instance ?: MusicRepository(local).also { instance = it }
    }
}
