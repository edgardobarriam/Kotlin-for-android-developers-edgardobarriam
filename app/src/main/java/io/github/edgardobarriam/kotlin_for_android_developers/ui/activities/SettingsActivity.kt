package io.github.edgardobarriam.kotlin_for_android_developers.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.github.edgardobarriam.kotlin_for_android_developers.R
import io.github.edgardobarriam.kotlin_for_android_developers.extensions.DelegatesExt
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    companion object {
        val ZIP_CODE = "zipCode"
        val DEFAULT_ZIP = 94043L
    }

    var zipCode: Long by DelegatesExt.preference(this, ZIP_CODE, DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        cityCode.setText(zipCode.toString())
    }

    override fun onBackPressed() {
        super.onBackPressed()
        zipCode = cityCode.text.toString().toLong()
    }
}
