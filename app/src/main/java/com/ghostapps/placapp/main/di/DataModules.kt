package com.ghostapps.placapp.main.di

import org.koin.dsl.module
import androidx.room.Room
import com.ghostapps.placapp.data.records.RecordDatabase
import com.ghostapps.placapp.data.records.RecordEntity
import com.ghostapps.placapp.data.records.useCases.DeleteLocalRegister
import com.ghostapps.placapp.data.records.useCases.GetAllLocalRegister
import com.ghostapps.placapp.data.records.useCases.InsertLocalRegister
import org.koin.android.ext.koin.androidContext

object DataModules {
    val modules = module {
        single {
            Room.databaseBuilder(
                androidContext(),
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

    }
}