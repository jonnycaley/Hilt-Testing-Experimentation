package com.example.hilt_testing_experimentation

import android.app.Application

open class Application : Application() {
    // potential @Inject issue here (see https://github.com/google/dagger/issues/2033#issuecomment-671504986 for context)
}