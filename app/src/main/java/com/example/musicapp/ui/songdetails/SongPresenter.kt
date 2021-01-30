package com.example.musicapp.ui.songdetails

import com.example.musicapp.data.model.Song
import com.example.musicapp.data.source.local.util.OnDataLocalCallback
import com.example.musicapp.data.source.repository.MusicDataSource
import com.example.musicapp.data.source.repository.MusicRepository
import java.lang.Exception

class SongPresenter(
    private val songView: SongContract.View,
    private val songRepository: MusicRepository
) : SongContract.Presenter {
    override fun getSongList(){
        songRepository.getSongList(object : OnDataLocalCallback<List<Song>>{
            override fun onSuccess(data: List<Song>) {
                songView.showSongList(data)
            }

            override fun onFail(exception: Exception?) {
                exception?.let {
                    songView.showError(it.message.toString())
                }
            }
        })
    }
}
