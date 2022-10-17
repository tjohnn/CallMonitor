package com.tjohnn.callmonitor.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.tjohnn.callmonitor.R
import com.tjohnn.callmonitor.data.server.LocalIpAddressFacade
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), UpNavigationCallBack {

    @Inject
    lateinit var localIpAddressFacade: LocalIpAddressFacade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun enableUpNavigation(enable: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(enable)
    }
}
