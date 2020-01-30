package com.example.comicviewer.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.comicviewer.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

private const val ARG_WRAPPER = "wrapper"

/**
 * A placeholder fragment containing a simple view.
 */
class ComicFragment : Fragment(), GetComicTask.ComicConsumer, GetImageTask.ImageConsumer {

    private lateinit var rootView: View
    private var wrapper: ComicWrapper? = null
    private var bitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            wrapper = it.getParcelable(ARG_WRAPPER)
            if (wrapper != null) {
                val urlString = "https://xkcd.com/${wrapper?.xkcdIssue}/info.0.json"
                GetComicTask(this).execute(urlString)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_main, container, false)
        rootView.section_label.text = wrapper?.comic?.safe_title ?: ""
        rootView.setBackgroundResource(wrapper!!.color)
        if (bitmap != null) {
            rootView.comic_image.setImageBitmap(bitmap)
        }
        return rootView
    }

    override fun onComicLoaded(comic: Comic?) {
        wrapper?.comic = comic
        rootView.section_label.text = comic?.safe_title
        GetImageTask(this).execute(comic?.img)
    }

    override fun onImageLoaded(bitmap: Bitmap?) {
        this.bitmap = bitmap
        rootView.comic_image.setImageBitmap(bitmap)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.mouseover -> {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Mouseover test for ${wrapper?.xkcdIssue}")
                builder.setMessage(wrapper?.comic?.alt)
                builder.create().show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, wrapper: ComicWrapper): ComicFragment {
            return ComicFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putParcelable(ARG_WRAPPER, wrapper)
                }
            }
        }
    }
}