package com.example.musicapp.utils

import android.content.Context
import com.example.musicapp.data.source.local.MusicLocalDataSource
import com.example.musicapp.data.source.local.dao.ResolverDataImp
import com.example.musicapp.data.source.repository.MusicRepository

object RepositoryFactory {
    fun getRepository(context: Context): MusicRepository {
        val resolverDataImp = ResolverDataImp.getInstance(context.contentResolver)
        val local = MusicLocalDataSource.getInstance(resolverDataImp)
        return MusicRepository.getInstance(local)
    }
}
