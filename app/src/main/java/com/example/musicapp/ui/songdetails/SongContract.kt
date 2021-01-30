package com.example.musicapp.ui.songdetails

import com.example.musicapp.data.model.Song

interface SongContract {
    interface View {
        fun showSongList(songList: List<Song>)
        fun showError(err: String)
    }

    interface Presenter {
        fun getSongList()
    }
}
