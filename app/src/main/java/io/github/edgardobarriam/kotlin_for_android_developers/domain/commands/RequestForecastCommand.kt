package io.github.edgardobarriam.kotlin_for_android_developers.domain.commands

import io.github.edgardobarriam.kotlin_for_android_developers.domain.datasource.ForecastProvider
import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.ForecastList

/**
 * Created by edgar on 22-03-2018.
 */
class RequestForecastCommand(
        private val zipCode: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider())
        : Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList = forecastProvider.requestByZipCode(zipCode, DAYS)
}