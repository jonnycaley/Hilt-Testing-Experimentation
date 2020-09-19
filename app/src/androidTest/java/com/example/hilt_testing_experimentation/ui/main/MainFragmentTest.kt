package com.example.hilt_testing_experimentation.ui.main

import com.example.hilt_testing_experimentation.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainFragmentTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @Before
    fun before() {
        rule.inject()
    }

    @Test
    fun test() {
        launchFragmentInHiltContainer<MainFragment> {

        }
        println("Start")
        println("Stop")
    }

}