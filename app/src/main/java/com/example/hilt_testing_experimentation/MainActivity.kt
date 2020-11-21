package com.example.hilt_testing_experimentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core_navigation.base.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, appNavigator.toMainFragment())
                .commitNow()
        }
    }
}