package com.example.hilt_testing_experimentation.utils

import android.view.View

infix fun View.visibleIf(condition: Boolean) {
    if (condition) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}