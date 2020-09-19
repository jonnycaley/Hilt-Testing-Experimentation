package com.example.hilt_testing_experimentation.ui.main

import com.example.hilt_testing_experimentation.domain.FakePokeRepository
import com.example.hilt_testing_experimentation.domain.PokeRepository
import com.example.hilt_testing_experimentation.domain.PokeRepositoryModule
import com.example.hilt_testing_experimentation.launchFragmentInHiltContainer
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(PokeRepositoryModule::class)
@HiltAndroidTest
class MainFragmentTest {

    @get:Rule
    val rule = HiltAndroidRule(this)

    @BindValue @JvmField
    val pokeRepository: PokeRepository = FakePokeRepository()

    @Before
    fun before() {
        rule.inject()
    }

    @Test
    fun test() {
        launchFragmentInHiltContainer<MainFragment> {

        }
    }

}