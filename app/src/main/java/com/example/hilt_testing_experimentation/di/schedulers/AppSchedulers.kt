package com.example.hilt_testing_experimentation.di.schedulers

import io.reactivex.rxjava3.core.Scheduler

open class AppSchedulers(
    val io: Scheduler,
    val computation: Scheduler,
    val mainThread: Scheduler
)