package com.example.musicapp.ui.songdetails

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.R
import com.example.musicapp.data.model.Song
import kotlinx.android.synthetic.main.item_song.view.*

class SongAdapter : RecyclerView.Adapter<SongAdapter.SongHolder>() {
    var listenerClick: (Song) -> Unit = { _ -> }
    private var songs = listOf<Song>()

    fun setSongList(songs: List<Song>) {
        this.songs = songs
        notifyDataSetChanged()
    }

    fun getSongList() = songs

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder =
        SongHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false),
            listenerClick
        )

    override fun getItemCount(): Int = songs.size

    override fun onBindViewHolder(holder: SongHolder, position: Int) =
        holder.bind(songs[position])

    class SongHolder(itemView: View, listenerClick: (Song) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var itemSong: Song? = null

        init {
            itemView.setOnClickListener {
                itemSong?.let {
                    listenerClick(
                        it
                    )
                }
            }
        }

        fun bind(song: Song) {
            itemSong = song
            itemView.apply {
                textTitleSong.text = song.title
                textArtist.text = song.artist
                val uri = Uri.parse("content://media/external/audio/albumart/" + song.id)
                imageSong.setImageURI(uri)
            }
        }
    }
}
