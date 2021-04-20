package com.ghostapps.placapp.main

import android.app.Application
import com.ghostapps.placapp.main.di.DataModules
import com.ghostapps.placapp.main.di.InfraModules
import com.ghostapps.placapp.main.di.ViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PlacApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PlacApp)
            modules(
                ViewModelModules.modules + DataModules.modules + InfraModules.modules
            )
        }

    }

}