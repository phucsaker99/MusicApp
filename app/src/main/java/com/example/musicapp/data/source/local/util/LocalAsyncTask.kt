package com.example.musicapp.data.source.local.util

import android.os.AsyncTask
import com.example.musicapp.R
import com.example.musicapp.data.model.Song
import java.lang.Exception

class LocalAsyncTask<P, R>(
    private val callBack: OnDataLocalCallback<R>,
    private val handle: (P) -> R
) : AsyncTask<P, Unit, R?>() {

    override fun doInBackground(vararg params: P): R? =
        try {
            handle(params[0])
        }catch (e: Exception){
            null
        }

    override fun onPostExecute(result: R?) {
        super.onPostExecute(result)
        result?.let { callBack.onSuccess(it) } ?: (callBack.onFail(Exception("Data null")))
    }

}
