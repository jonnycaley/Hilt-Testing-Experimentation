package com.example.hilt_testing_experimentation.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText

class MainFragmentRobot {

    fun checkErrorDisplayed(): MainFragmentRobot {
        onView(withText("An error occurred, try again later"))
            .check(matches(isDisplayed()))
        return this
    }

    fun checkLoadingDisplayed(): MainFragmentRobot {
        onView(withText("Loading..."))
            .check(matches(isDisplayed()))
        return this
    }
}