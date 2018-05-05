package io.github.edgardobarriam.kotlin_for_android_developers.data.server

import io.github.edgardobarriam.kotlin_for_android_developers.data.db.ForecastDb
import io.github.edgardobarriam.kotlin_for_android_developers.domain.datasource.ForecastDataSource
import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.Forecast
import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.ForecastList

class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb =  ForecastDb()) : ForecastDataSource {


    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast? {
        throw UnsupportedOperationException()
    }
}