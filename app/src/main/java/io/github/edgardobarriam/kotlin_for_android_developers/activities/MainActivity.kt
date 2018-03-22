package io.github.edgardobarriam.kotlin_for_android_developers.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.github.edgardobarriam.kotlin_for_android_developers.Forecast
import io.github.edgardobarriam.kotlin_for_android_developers.R
import io.github.edgardobarriam.kotlin_for_android_developers.Request
import io.github.edgardobarriam.kotlin_for_android_developers.adapters.ForecastListAdapter
import org.jetbrains.anko.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Sunny - 31/17",
            "Fri 6/27 - Foggy - 21/8",
            "Sat 6/28 - TRAPPED IN WHEATERSTATION - 22/17"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewForecastList : RecyclerView = find(R.id.recyclerViewForecastList)
        recyclerViewForecastList.layoutManager = LinearLayoutManager(this)
        recyclerViewForecastList.adapter = ForecastListAdapter(items)

        val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
                "id=3874960&APPID=7b395ffdfd76eb4cc5137f557a42b4bf"

        doAsync {
            Request(url).run()
            uiThread { longToast("Request performed") }
        }

        val myForecast = Forecast(Date(), 27.5F, "Shiny Day")
        val (date, temperature, details) = myForecast   // destructuring

        // toast(R.string.hello_world)
    }
}