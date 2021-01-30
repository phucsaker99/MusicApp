package com.example.musicapp.data.source.local

import com.example.musicapp.data.model.Artist
import com.example.musicapp.data.model.Song
import com.example.musicapp.data.source.local.dao.ResolverDataDao
import com.example.musicapp.data.source.local.util.LocalAsyncTask
import com.example.musicapp.data.source.local.util.OnDataLocalCallback
import com.example.musicapp.data.source.repository.MusicDataSource

class MusicLocalDataSource(private val resolverDataDao: ResolverDataDao) : MusicDataSource.Local {
    override fun getSongList(callback: OnDataLocalCallback<List<Song>>) {
        LocalAsyncTask<Unit, List<Song>>(callback) {
            resolverDataDao.getSongList()
        }.execute(Unit)
    }

    override fun getArtistList(callback: OnDataLocalCallback<List<Artist>>) {
        LocalAsyncTask<Unit, List<Artist>>(callback) {
            resolverDataDao.getArtistList()
        }.execute(Unit)
    }

    companion object {
        private var instance: MusicLocalDataSource? = null

        fun getInstance(resolverDataDao: ResolverDataDao): MusicLocalDataSource =
            instance ?: synchronized(this) {
                instance ?: MusicLocalDataSource(resolverDataDao).also {
                    instance = it
                }
            }
    }
}
