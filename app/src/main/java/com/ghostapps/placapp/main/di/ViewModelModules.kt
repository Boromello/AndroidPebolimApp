package com.ghostapps.placapp.main.di

import com.ghostapps.placapp.data.records.local.useCases.DeleteLocalRegister
import com.ghostapps.placapp.data.records.local.useCases.GetAllLocalRegister
import com.ghostapps.placapp.data.records.local.useCases.InsertLocalRegister
import com.ghostapps.placapp.data.records.remote.useCases.GetAllRemoteRegister
import com.ghostapps.placapp.viewModel.gameRecords.GameRecordsViewModel
import com.ghostapps.placapp.viewModel.gameScore.GameScoreContract
import com.ghostapps.placapp.viewModel.gameScore.GameScoreViewModel
import com.ghostapps.placapp.viewModel.home.HomeContract
import com.ghostapps.placapp.viewModel.home.HomeViewModel
import com.ghostapps.placapp.viewModel.preGame.PreGameContract
import com.ghostapps.placapp.viewModel.preGame.PreGameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModules {

    val modules = module {
        viewModel { (contract: HomeContract) ->
            HomeViewModel(contract)
        }
        viewModel {(contract: PreGameContract) ->
            PreGameViewModel(contract)
        }
        viewModel {(contract: GameScoreContract) ->
            GameScoreViewModel(contract, get<InsertLocalRegister>())
        }
        viewModel {
            GameRecordsViewModel(get<GetAllLocalRegister>(), get<DeleteLocalRegister>())
        }
    }

}