package com.example.comicviewer

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.viewpager.widget.PagerTitleStrip
import com.example.comicviewer.ui.main.ComicsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val comicsPagerAdapter = ComicsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = comicsPagerAdapter
        val fab: FloatingActionButton = findViewById(R.id.fab)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            comicsPagerAdapter.add()
        }
    }
}