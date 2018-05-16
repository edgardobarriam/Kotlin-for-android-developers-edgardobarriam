package io.github.edgardobarriam.kotlin_for_android_developers

import io.github.edgardobarriam.kotlin_for_android_developers.domain.commands.RequestDayForecastCommand
import io.github.edgardobarriam.kotlin_for_android_developers.domain.datasource.ForecastProvider
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RequestDayForecastCommandTest {

    @Test
    fun testProviderIsCalled() {
        val forecastProvider = mock(ForecastProvider::class.java)
        val command = RequestDayForecastCommand(2, forecastProvider)

        command.execute()

        verify(forecastProvider).requestForecast(2)
    }
}