package io.github.edgardobarriam.kotlin_for_android_developers.domain.datasource

import io.github.edgardobarriam.kotlin_for_android_developers.data.db.ForecastDb
import io.github.edgardobarriam.kotlin_for_android_developers.data.server.ForecastServer
import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.ForecastList
import io.github.edgardobarriam.kotlin_for_android_developers.extensions.firstResult

class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 24 * 60 * 60 * 1000
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList
        = sources.firstResult { requestSource(it, days, zipCode) }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
               val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
               return if (res != null && res.size >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}