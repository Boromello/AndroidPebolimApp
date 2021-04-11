package com.ghostapps.placapp.main

import android.app.Application
import com.ghostapps.placapp.main.di.DataModules
import com.ghostapps.placapp.main.di.ViewModelModules
import org.koin.core.context.startKoin

class PlacApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                ViewModelModules.modules + DataModules.modules
            )
        }

    }

}