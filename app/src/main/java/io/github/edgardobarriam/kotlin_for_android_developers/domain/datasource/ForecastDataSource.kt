package io.github.edgardobarriam.kotlin_for_android_developers.domain.datasource

import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.Forecast
import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?
}