package io.github.edgardobarriam.kotlin_for_android_developers.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import io.github.edgardobarriam.kotlin_for_android_developers.R
import io.github.edgardobarriam.kotlin_for_android_developers.data.Coordinates
import io.github.edgardobarriam.kotlin_for_android_developers.domain.commands.RequestForecastCommand
import io.github.edgardobarriam.kotlin_for_android_developers.extensions.DelegatesExt
import io.github.edgardobarriam.kotlin_for_android_developers.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    val zipCode: Long by DelegatesExt.preference(this, SettingsActivity.ZIP_CODE,
            SettingsActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        recyclerViewForecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(recyclerViewForecastList)

        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city)
                }
                recyclerViewForecastList.adapter = adapter
                toolbarTitle = "${result.city} (${result.country}"
            }
        }

        val myCoordinates = Coordinates(1.2F, 1.5F)
        val (lat, lon) = myCoordinates   // destructuring

    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = doAsync {
        val result = RequestForecastCommand(zipCode).execute()
        uiThread {
            val adapter = ForecastListAdapter(result) {
                startActivity<DetailActivity>(DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to result.city)
            }
            recyclerViewForecastList.adapter = adapter
            toolbarTitle = "${result.city} (${result.country}"
        }
    }
}