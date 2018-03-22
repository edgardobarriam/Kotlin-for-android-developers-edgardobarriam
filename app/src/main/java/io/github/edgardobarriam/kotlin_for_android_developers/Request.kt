package io.github.edgardobarriam.kotlin_for_android_developers

import android.util.Log
import java.net.URL

/**
 * Created by edgar on 22-03-2018.
 */
class Request(val url: String) {

    fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJsonStr)
    }
}