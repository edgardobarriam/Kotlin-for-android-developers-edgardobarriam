package io.github.edgardobarriam.kotlin_for_android_developers.ui

import android.app.Application
import io.github.edgardobarriam.kotlin_for_android_developers.ui.utils.DelegatesExt

class App : Application() {

    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()

        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}