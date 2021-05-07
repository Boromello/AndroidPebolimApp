package com.ghostapps.placapp.main.di

import com.ghostapps.placapp.data.records.local.useCases.DeleteLocalRegister
import com.ghostapps.placapp.data.records.local.useCases.GetAllLocalRegister
import com.ghostapps.placapp.data.records.local.useCases.InsertLocalRegister
import com.ghostapps.placapp.data.records.remote.useCases.*
import com.ghostapps.placapp.viewModel.gameRecords.GameRecordsViewModel
import com.ghostapps.placapp.viewModel.gameScore.GameScoreContract
import com.ghostapps.placapp.viewModel.gameScore.GameScoreViewModel
import com.ghostapps.placapp.viewModel.home.HomeContract
import com.ghostapps.placapp.viewModel.home.HomeViewModel
import com.ghostapps.placapp.viewModel.login.LoginContract
import com.ghostapps.placapp.viewModel.login.LoginViewModel
import com.ghostapps.placapp.viewModel.preGame.PreGameContract
import com.ghostapps.placapp.viewModel.preGame.PreGameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModules {

    val modules = module {
        viewModel { (contract: LoginContract) ->
            LoginViewModel(contract, get<LoginFirebase>())
        }
        viewModel { (contract: HomeContract) ->
            HomeViewModel(contract)
        }
        viewModel {(contract: PreGameContract) ->
            PreGameViewModel(contract)
        }
        viewModel {(contract: GameScoreContract) ->
            GameScoreViewModel(contract, get<InsertFirebaseRecord>())
        }
        viewModel {
            GameRecordsViewModel(get<GetAllFirebaseRecord>(), get<DeleteFirebaseRecord>())
        }
    }

}