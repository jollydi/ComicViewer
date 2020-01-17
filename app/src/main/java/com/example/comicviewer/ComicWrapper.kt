package com.example.comicviewer

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ComicWrapper(var color: Int = 0, var xkcdIssue: Int = 614) : Parcelable {

    init {
        color =
            if (iterator.hasNext()) {
                iterator.next()
            } else {
                iterator = COLORS.iterator()
                iterator.next()
            }
        xkcdIssue = Utils.randomCleanIssueNumber()

    }

    companion object {
        private val COLORS = listOf<Int>(
            android.R.color.holo_green_light,
            android.R.color.holo_blue_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light
        )
        private var iterator = COLORS.iterator()
    }
}