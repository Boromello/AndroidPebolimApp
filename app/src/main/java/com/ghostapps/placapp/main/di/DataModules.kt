package com.ghostapps.placapp.main.di

import org.koin.dsl.module
import androidx.room.Room
import com.ghostapps.placapp.data.records.local.RecordDatabase
import com.ghostapps.placapp.data.records.RecordEntity
import com.ghostapps.placapp.data.records.local.useCases.DeleteLocalRegister
import com.ghostapps.placapp.data.records.local.useCases.GetAllLocalRegister
import com.ghostapps.placapp.data.records.local.useCases.InsertLocalRegister
import com.ghostapps.placapp.data.records.remote.useCases.*
import com.ghostapps.placapp.infra.http.HttpAdapter
import com.google.firebase.auth.FirebaseAuth
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
            InsertFirebaseRecord(get())
        }

        factory {
            DeleteFirebaseRecord(get())
        }

        factory {
            GetAllFirebaseRecord()
        }

        factory {
            GetAllRemoteRegister(get())
        }

        factory {
            LoginFirebase(get())
        }

    }
}