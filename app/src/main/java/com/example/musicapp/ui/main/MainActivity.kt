package com.example.musicapp.ui.main

import android.Manifest
import android.animation.ObjectAnimator
import android.content.*
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import com.example.musicapp.R
import com.example.musicapp.controller.MediaController
import com.example.musicapp.data.source.local.dao.ResolverDataImp
import com.example.musicapp.ui.artistdetails.ArtistDetailFragment
import com.example.musicapp.ui.notification.SongService
import com.example.musicapp.ui.songdetails.SongDetailFragment
import com.example.musicapp.utils.NewsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var adapterPage: NewsPagerAdapter? = null
    private var fmSong: SongDetailFragment? = null
    private var fmArtist: ArtistDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (checkPermission()) {
            initView()
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(PERMISSION, 0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connectionService)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (checkPermission()) {
            initView()
        }
    }

    private fun initView() {
        fmSong = SongDetailFragment.getInstance()
        fmArtist = ArtistDetailFragment.getInstance()
        serviceSong = SongService.getInstance()
        resolverMusic = ResolverDataImp.getInstance(this.contentResolver)
        mediaMusic = MediaController.getInstance(resolverMusic!!.getSongList(), this)
        adapterPage = NewsPagerAdapter(supportFragmentManager, fmSong!!, fmArtist!!)
        viewPagerMusic.adapter = adapterPage
        tabLayoutMusic.setupWithViewPager(viewPagerMusic)
        val intent = SongService.getIntent(this)
        bindService(intent, connectionService, Context.BIND_AUTO_CREATE)
    }

    private var connectionService = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val songBinder = service as SongService.SongServiceBinder
            serviceSong = songBinder.getService()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Toast.makeText(this@MainActivity, "Connect fails", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (p in PERMISSION) {
                val status = checkSelfPermission(p)
                if (status == PackageManager.PERMISSION_DENIED) {
                    return false
                }
            }
        }
        return true
    }

    companion object {
        val PERMISSION = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        var resolverMusic: ResolverDataImp? = null
        var serviceSong: SongService? = null
        var mediaMusic: MediaController? = null
    }
}
