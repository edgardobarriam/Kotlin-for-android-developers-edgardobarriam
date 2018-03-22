package io.github.edgardobarriam.kotlin_for_android_developers.domain.model


/**
 * Created by edgar on 22-03-2018.
 */

data class ForecastList(val city: String, val country: String,
                        val dailyForecast: List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int, val low: Int)