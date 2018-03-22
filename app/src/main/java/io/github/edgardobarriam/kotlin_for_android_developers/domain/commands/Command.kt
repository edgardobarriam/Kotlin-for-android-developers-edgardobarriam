package io.github.edgardobarriam.kotlin_for_android_developers.domain.commands

/**
 * Created by edgar on 22-03-2018.
 */
interface Command<out T>{
    fun execute(): T
}