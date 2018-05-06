package io.github.edgardobarriam.kotlin_for_android_developers.extensions

import android.view.View

fun View.slideEnter() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideExit() {
    if (translationY < 0f) animate().translationY(0f)
}