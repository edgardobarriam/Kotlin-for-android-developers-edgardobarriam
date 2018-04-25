package io.github.edgardobarriam.kotlin_for_android_developers.data.db

import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.ForecastList
import io.github.edgardobarriam.kotlin_for_android_developers.extensions.clear
import org.jetbrains.anko.db.select
import  io.github.edgardobarriam.kotlin_for_android_developers.extensions.parseList
import io.github.edgardobarriam.kotlin_for_android_developers.extensions.parseOpt
import io.github.edgardobarriam.kotlin_for_android_developers.extensions.toVarargArray
import org.jetbrains.anko.db.insert

class ForecastDb (val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                  val dataMapper: DbDataMapper = DbDataMapper() ) {

    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use{
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString() )
                .parseList { DayForecast(HashMap(it)) }

        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }

        // Last line inside a lambda represents what the lambda returns
        if (city != null) dataMapper.convertToDomain(city) else null
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {

        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }
    }
}