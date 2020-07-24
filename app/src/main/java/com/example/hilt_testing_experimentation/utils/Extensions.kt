package com.example.hilt_testing_experimentation.utils

import android.view.View

infix fun View.visibleIf(condition: Boolean) {
    if (condition) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun String.getOffsetFromUrl(): Int? {
    return try {
        this.substring(this.lastIndexOf("offset=") + 7, this.indexOf("&limit")).toInt()
    } catch (thr: Throwable) {
        null
    }
}