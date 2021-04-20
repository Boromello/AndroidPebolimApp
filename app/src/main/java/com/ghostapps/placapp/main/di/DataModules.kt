package com.ghostapps.placapp.main.di

import org.koin.dsl.module
import androidx.room.Room
import com.ghostapps.placapp.data.records.local.RecordDatabase
import com.ghostapps.placapp.data.records.RecordEntity
import com.ghostapps.placapp.data.records.local.useCases.DeleteLocalRegister
import com.ghostapps.placapp.data.records.local.useCases.GetAllLocalRegister
import com.ghostapps.placapp.data.records.local.useCases.InsertLocalRegister
import com.ghostapps.placapp.data.records.remote.useCases.GetAllRemoteRegister
import com.ghostapps.placapp.infra.http.HttpAdapter
import org.koin.android.ext.koin.androidApplication

object DataModules {
    val modules = module {
        single {
            Room.databaseBuilder(
                androidApplication(),
                RecordDatabase::class.java,
                RecordEntity.TABLE_NAME
            ).fallbackToDestructiveMigration().build()
        }

        factory {
            InsertLocalRegister(get())
        }

        factory {
            DeleteLocalRegister(get())
        }

        factory {
            GetAllLocalRegister(get())
        }

        factory {
            GetAllRemoteRegister(get())
        }

    }
}