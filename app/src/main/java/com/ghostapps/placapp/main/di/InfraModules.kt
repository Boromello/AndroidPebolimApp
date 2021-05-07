package com.ghostapps.placapp.main.di

import com.ghostapps.placapp.infra.http.HttpAdapter
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

object InfraModules {
    val modules = module {
        factory {
            HttpAdapter()
        }
        factory { FirebaseAuth.getInstance() }
    }
}