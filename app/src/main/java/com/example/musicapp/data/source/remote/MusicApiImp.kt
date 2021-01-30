package com.example.musicapp.data.source.remote

import android.content.Context

class MusicApiImp(context: Context) {
    companion object {
        private var instance: MusicApiImp? = null
        fun getInstance(context: Context) = instance ?: MusicApiImp(context).also {
            instance = it
        }
    }
}
