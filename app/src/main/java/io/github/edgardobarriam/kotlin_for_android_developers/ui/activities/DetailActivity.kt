package io.github.edgardobarriam.kotlin_for_android_developers.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.github.edgardobarriam.kotlin_for_android_developers.R
import io.github.edgardobarriam.kotlin_for_android_developers.domain.commands.RequestDayForecastCommand
import io.github.edgardobarriam.kotlin_for_android_developers.domain.model.Forecast
import io.github.edgardobarriam.kotlin_for_android_developers.extensions.color
import io.github.edgardobarriam.kotlin_for_android_developers.extensions.toDateString
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.textColor
import org.jetbrains.anko.uiThread
import java.text.DateFormat

class DetailActivity : AppCompatActivity() {

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = intent.getStringExtra(CITY_NAME)

        doAsync {
            val forecastId = intent.getLongExtra(ID,-1)
            val result = RequestDayForecastCommand(forecastId).execute()
            uiThread { bindForecast(result) }
        }


    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.get().load(iconUrl).into(icon)
        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}
