package com.ghostapps.placapp.viewModel.home

import androidx.lifecycle.ViewModel
import com.ghostapps.placapp.ui.gameRecords.GameRecordsActivity
import com.ghostapps.placapp.ui.preGame.PreGameActivity

class HomeViewModel(
    val contract: HomeContract
) : ViewModel() {

    fun onNewGamePressed() {
        contract.navigateTo(PreGameActivity::class.java)
    }

    fun onRecordsPressed() {
        contract.navigateTo(GameRecordsActivity::class.java)
    }

    fun onExitPressed() {
        contract.closeApp()
    }
}