package io.github.edgardobarriam.kotlin_for_android_developers.domain.commands

import io.github.edgardobarriam.kotlin_for_android_developers.domain.mappers.ForecastDataMapper
import io.github.edgardobarriam.kotlin_for_android_developers.data.ForecastRequest
import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.ForecastList

/**
 * Created by edgar on 22-03-2018.
 */
class RequestForecastCommand(val zipCode: String) : Command<ForecastList>{

    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }

}