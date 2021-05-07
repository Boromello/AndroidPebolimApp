package com.ghostapps.placapp.viewModel.gameScore

import android.util.Log
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.InsertRegister
import com.ghostapps.placapp.viewModel.BaseViewModel
import java.util.*

class GameScoreViewModel(
    private val contract: GameScoreContract,
    private val insertRegister: InsertRegister
): BaseViewModel() {

    var homeTeamName = ""
    var awayTeamName = ""

    private var homeTeamScore = 0
    private var awayTeamScore = 0
    private var homeTeamScoreSet = 0
    private var awayTeamScoreSet = 0
    private var homeTeamScoreAdvPoint = 0
    private var awayTeamScoreAdvPoint = 0

    var formattedHomeTeamScore = "00"
    var formattedAwayTeamScore = "00"
    var formattedHomeTeamScoreSet = "00"
    var formattedAwayTeamScoreSet = "00"
    var formattedHomeTeamScoreAdvPoint = "00"
    var formattedAwayTeamScoreAdvPoint = "00"

    fun onCreate(homeTeamName: String, awayTeamName: String) {
        this.homeTeamName = homeTeamName
        this.awayTeamName = awayTeamName
    }

    fun onHomeTeamIncrease() {
        if ((homeTeamScore == 10) && (awayTeamScore == 10)) {
            updateScoreHomeAdvPoint()
        }else {
            homeTeamScore++
        }
        updateScore()
    }

    fun onHomeTeamDecrease() {
        if (homeTeamScore > 0) homeTeamScore--
        homeTeamScoreAdvPoint = 0
        awayTeamScoreAdvPoint = 0
        formattedHomeTeamScoreAdvPoint = String.format("%02d", homeTeamScoreAdvPoint)
        formattedAwayTeamScoreAdvPoint = String.format("%02d", awayTeamScoreAdvPoint)
        updateScore()
    }

    fun onAwayTeamIncrease() {
        if ((homeTeamScore == 10) && (awayTeamScore == 10)) {
            updateScoreAwayAdvPoint()
        }else {
            awayTeamScore++
        }
        updateScore()
    }

    fun onAwayTeamDecrease() {
        if (awayTeamScore > 0) awayTeamScore--
        homeTeamScoreAdvPoint = 0
        awayTeamScoreAdvPoint = 0
        formattedHomeTeamScoreAdvPoint = String.format("%02d", homeTeamScoreAdvPoint)
        formattedAwayTeamScoreAdvPoint = String.format("%02d", awayTeamScoreAdvPoint)
        updateScore()
    }

    fun onExitPressed() {
        /*DESATIVEI POIS A MINHA INSERCAO SE DA NO MOMENTO QUE UM DOS JOGADORES ATINGE OS
             11 PTS
        Thread {

            val success = insertRegister.execute(RecordModel(
                homeTeamName = homeTeamName,
                homeTeamScore = homeTeamScore,
                awayTeamName = awayTeamName,
                awayTeamScore = awayTeamScore,
                date = Date().time
            ))*/

            contract.onExitPressed()
            /*
            if (success) {
                contract.onExitPressed()
            } else {
                contract.onInsertRegisterFails()
            }

        }.start()*/
    }

    fun updateScore() {
        if ((homeTeamScore == 10) && (awayTeamScore == 10) && ((homeTeamScoreAdvPoint > 0) || (awayTeamScoreAdvPoint > 0))) {
            updateScoreAdvPoint()
        }else if ((homeTeamScore == 11) || (awayTeamScore == 11)) {
            Thread {
            insertRegister.execute(RecordModel(
                homeTeamName = homeTeamName,
                homeTeamScore = homeTeamScore,
                awayTeamName = awayTeamName,
                awayTeamScore = awayTeamScore,
                date = Date().time
            ))
                updateScoreSet()
            }.start()
        }
        formattedHomeTeamScore = String.format("%02d", homeTeamScore)
        formattedAwayTeamScore = String.format("%02d", awayTeamScore)

        notifyChange()
    }

    fun updateScoreSet() {
        if ((homeTeamScore == 11) || (homeTeamScoreAdvPoint - awayTeamScoreAdvPoint == 2)) {
            homeTeamScoreSet++
        }else if ((awayTeamScore == 11) || (awayTeamScoreAdvPoint - homeTeamScoreAdvPoint == 2)) {
            awayTeamScoreSet++
        }
        homeTeamScore = 0
        awayTeamScore = 0
        homeTeamScoreAdvPoint = 0
        awayTeamScoreAdvPoint = 0
        formattedHomeTeamScoreAdvPoint = String.format("%02d", homeTeamScoreAdvPoint)
        formattedAwayTeamScoreAdvPoint = String.format("%02d", awayTeamScoreAdvPoint)
        formattedHomeTeamScoreSet = String.format("%02d", homeTeamScoreSet)
        formattedAwayTeamScoreSet = String.format("%02d", awayTeamScoreSet)

        if((homeTeamScoreSet == 7) || (awayTeamScoreSet == 7)) {
            if (homeTeamScore > awayTeamScore) {
                Log.i("Vencedor", "O Campeão é: " + awayTeamName)
            }else {
                Log.i("Vencedor", "O Campeão é: " + homeTeamName)
            }
            homeTeamScoreSet = 0
            awayTeamScoreSet = 0
            contract.onExitPressed()
        }
    }

    fun updateScoreAdvPoint() {
        if ((homeTeamScoreAdvPoint > awayTeamScoreAdvPoint ) && (homeTeamScoreAdvPoint - awayTeamScoreAdvPoint == 2)) {
            updateScoreSet()
        }else if ((awayTeamScoreAdvPoint > homeTeamScoreAdvPoint) && (awayTeamScoreAdvPoint - homeTeamScoreAdvPoint == 2)) {
            updateScoreSet()
        }
    }

    fun updateScoreHomeAdvPoint() {
        homeTeamScoreAdvPoint++
        formattedHomeTeamScoreAdvPoint = String.format("%02d", homeTeamScoreAdvPoint)
    }

    fun updateScoreAwayAdvPoint() {
        awayTeamScoreAdvPoint++
        formattedAwayTeamScoreAdvPoint = String.format("%02d", awayTeamScoreAdvPoint)
    }
}