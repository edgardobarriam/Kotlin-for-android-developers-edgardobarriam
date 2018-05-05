package io.github.edgardobarriam.kotlin_for_android_developers.domain.commands

import io.github.edgardobarriam.kotlin_for_android_developers.domain.datasource.ForecastProvider
import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.Forecast

class RequestDayForecastCommand (val id: Long,
                                 val forecastProvider: ForecastProvider = ForecastProvider()) :
                                 Command<Forecast> {
    override fun execute() = forecastProvider.requestForecast(id)
}