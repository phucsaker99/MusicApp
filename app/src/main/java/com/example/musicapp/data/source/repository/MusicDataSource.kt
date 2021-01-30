package com.example.musicapp.data.source.repository

import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.model.Song
import com.example.musicapp.data.source.local.util.OnDataLocalCallback

interface MusicDataSource {
    interface Local {
        fun getSongList(callback: OnDataLocalCallback<List<Song>>)
        fun getArtistList(callback: OnDataLocalCallback<List<Artist>>)
    }
}
