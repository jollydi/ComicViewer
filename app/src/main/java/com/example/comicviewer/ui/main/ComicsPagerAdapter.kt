package com.example.comicviewer.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.comicviewer.ComicWrapper
import com.example.comicviewer.R

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class ComicsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    val wrappers = arrayListOf<ComicWrapper>(ComicWrapper(), ComicWrapper(), ComicWrapper(), ComicWrapper(), ComicWrapper())

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a ComicFragment (defined as a static inner class below).
        return ComicFragment.newInstance(position + 1, wrappers[position])
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Issue ${wrappers[position].xkcdIssue}"
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return wrappers.size
    }

    fun add () {
        wrappers.add(ComicWrapper())
        notifyDataSetChanged()
    }
}