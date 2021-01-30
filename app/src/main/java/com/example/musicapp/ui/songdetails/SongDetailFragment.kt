package com.example.musicapp.ui.songdetails

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.musicapp.R
import com.example.musicapp.controller.MediaController
import com.example.musicapp.data.model.Song
import com.example.musicapp.data.source.local.dao.ResolverDataImp
import com.example.musicapp.data.source.repository.MusicDataSource
import com.example.musicapp.data.source.repository.MusicRepository
import com.example.musicapp.ui.main.MainActivity
import com.example.musicapp.ui.notification.SongService
import com.example.musicapp.utils.RepositoryFactory
import kotlinx.android.synthetic.main.fragment_detail_song.*

class SongDetailFragment : Fragment(R.layout.fragment_detail_song), SongContract.View {
    private var songAdapter = SongAdapter()
    private var songPresenter: SongPresenter? = null
    private var indexSong = -1
    private var songs: List<Song> = listOf()
    private var serviceSong: SongService? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        val repository = RepositoryFactory.getRepository(context!!)
        songPresenter = activity?.contentResolver?.let { SongPresenter(this, repository) }
        songAdapter.listenerClick = this::clickListenerItemSong
        songPresenter?.getSongList()
        recyclerSong.adapter = songAdapter
        //MainActivity.mediaMusic = MediaController(songs, context!!)
    }

    override fun showSongList(songList: List<Song>) {
        this.songs = songList
        songAdapter.setSongList(songs)
        songAdapter.notifyDataSetChanged()
    }

    override fun showError(err: String) {
        Toast.makeText(context, err, Toast.LENGTH_LONG).show()
    }

    private fun clickListenerItemSong(song: Song) {
        serviceSong = SongService.getInstance()
        indexSong = songs.indexOf(song)
        MediaController.getInstance(songs, context!!).create(indexSong)
        songAdapter.let { MainActivity.serviceSong?.setSongList(it.getSongList()) }
    }

    companion object {
        private var instance: SongDetailFragment? = null
        fun getInstance() = instance ?: SongDetailFragment().also {
            instance = it
        }
    }
}


