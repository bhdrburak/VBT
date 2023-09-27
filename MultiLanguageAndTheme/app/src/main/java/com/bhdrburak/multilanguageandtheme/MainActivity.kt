package com.bhdrburak.multilanguageandtheme

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.bhdrburak.multilanguageandtheme.Constant.authKey
import java.util.Locale

class MainActivity : AppCompatActivity() {

    var isTr = true
    var themeCode = "daily"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.buttonChange)


        button.text = resources.getString(R.string.class_name)



    }



    private fun setLocale(languageCode: String, context: Context) {
        val configUpdated = applyLocale(context, languageCode)
        if (configUpdated) {
            finish()
            startActivity(intent)
        }
    }

    private fun getCurrentLanguage(): String {
        val currentLanguage: Locale = Locale.getDefault()
        return currentLanguage.language
    }

    private fun applyLocale(context: Context, languageCode: String): Boolean {
        val config = context.resources.configuration

        val oldLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.locales.get(0)
        } else {
            config.locale
        }

        val newLocale = Locale(languageCode)

        if (oldLocale == newLocale) {
            return false
        }

        Locale.setDefault(newLocale)

        config.setLocale(newLocale)

        context.createConfigurationContext(config)

        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        return true

    }

}