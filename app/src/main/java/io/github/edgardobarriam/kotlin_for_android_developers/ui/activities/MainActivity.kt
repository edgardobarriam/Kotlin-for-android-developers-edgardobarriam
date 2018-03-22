package io.github.edgardobarriam.kotlin_for_android_developers.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import io.github.edgardobarriam.kotlin_for_android_developers.R
import io.github.edgardobarriam.kotlin_for_android_developers.data.Coordinates
import io.github.edgardobarriam.kotlin_for_android_developers.data.ForecastRequest
import io.github.edgardobarriam.kotlin_for_android_developers.domain.commands.RequestForecastCommand
import io.github.edgardobarriam.kotlin_for_android_developers.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.*

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

        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread {
                recyclerViewForecastList.adapter = ForecastListAdapter(result)
            }
        }

        val myCoordinates = Coordinates(1.2F, 1.5F)
        val (lat, lon) = myCoordinates   // destructuring

    }
}