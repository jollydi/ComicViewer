package com.example.comicviewer

import android.os.AsyncTask
import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.*
import java.lang.Exception
import java.net.URL

class GetComicTask(private var comicConsumer: ComicConsumer): AsyncTask<String, Void, Comic>() {

    override fun doInBackground(vararg params: String?): Comic? {
        val url = URL(params[0])
        return try {
            val s = url.readText()
            val mapper = ObjectMapper().registerModule(KotlinModule())
            val comic = mapper.readValue<Comic>(s)
            comic
        } catch (e: Exception) {
            Log.e(Constants.TAG, "EXCEPTION: " + e.toString())
            null
        }
    }

    override fun onPostExecute(result: Comic?) {
        super.onPostExecute(result)
        comicConsumer.onComicLoaded(result)
    }

    interface ComicConsumer {
        fun onComicLoaded(comic: Comic?)
    }

}