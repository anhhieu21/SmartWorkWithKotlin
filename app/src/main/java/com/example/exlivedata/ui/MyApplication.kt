package com.example.exlivedata.ui

import android.app.Application
import androidx.lifecycle.ViewModelStore

class MyApplication : Application() {
    val appViewModelStore: ViewModelStore by lazy { ViewModelStore() }
}