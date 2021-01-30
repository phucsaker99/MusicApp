package com.example.musicapp.data.source.local.dao

import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.model.Song

interface ResolverDataDao {
    fun getSongList(): MutableList<Song>
    fun getArtistList(): MutableList<Artist>
}
