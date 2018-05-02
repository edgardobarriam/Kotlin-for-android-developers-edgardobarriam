package io.github.edgardobarriam.kotlin_for_android_developers.domain.datasource

import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}