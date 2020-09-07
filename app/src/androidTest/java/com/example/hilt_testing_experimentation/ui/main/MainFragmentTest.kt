package com.example.hilt_testing_experimentation.ui.main

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
        println("Start")
        println("Stop")
    }

}