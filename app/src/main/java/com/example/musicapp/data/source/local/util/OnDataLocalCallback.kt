package com.example.musicapp.data.source.local.util

import java.lang.Exception

interface OnDataLocalCallback<T> {
    fun onSuccess(data: T)
    fun onFail(exception: Exception? = Exception("Data null"))
}
