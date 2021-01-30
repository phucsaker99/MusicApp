package com.example.musicapp.ui.artistdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.musicapp.R
import com.example.musicapp.data.model.Artist
import com.example.musicapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_detail_artist.*

class ArtistDetailFragment : Fragment(R.layout.fragment_detail_artist) {
    private var artists: MutableList<Artist>? = null
    private var adapterArtist = ArtistAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        artists = MainActivity.resolverMusic?.getArtistList()
        artists?.let { adapterArtist.setArtist(it) }
        recyclerArtist.adapter = adapterArtist
    }

    companion object {
        private var instance: ArtistDetailFragment? = null
        fun getInstance() = instance ?: ArtistDetailFragment().also {
            instance = it
        }
    }
}
