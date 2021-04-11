package com.ghostapps.placapp.viewModel.gameScore

import android.app.AlertDialog
import android.util.Log
import com.ghostapps.placapp.viewModel.BaseViewModel

class GameScoreViewModel(
    private val contract: GameScoreContract
): BaseViewModel() {

    var homeTeamName = ""
    var awayTeamName = ""

    var homeTeamScore = 0
    var awayTeamScore = 0
    var homeTeamScoreSet = 0
    var awayTeamScoreSet = 0
    var homeTeamScoreAdvPoint = 0
    var awayTeamScoreAdvPoint = 0

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
        contract.onExitPressed()
    }

    private fun updateScore() {
        if ((homeTeamScore == 10) && (awayTeamScore == 10) && ((homeTeamScoreAdvPoint > 0) || (awayTeamScoreAdvPoint > 0))) {
            updateScoreAdvPoint()
        }else if ((homeTeamScore == 11) || (awayTeamScore == 11)) {
            updateScoreSet()
        }
        formattedHomeTeamScore = String.format("%02d", homeTeamScore)
        formattedAwayTeamScore = String.format("%02d", awayTeamScore)

        notifyChange()
    }

    private fun updateScoreSet() {
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
            //NAO CONSEGUI COLOCAR A CAIXA DE DIALOGO PARA EXIBIR O CAMPEAO
//            val builder = AlertDialog.Builder(this.contract.)
//            builder.setTitle("Temos um Vencedor!")
//            if (homeTeamScore > awayTeamScore) {
//                builder.setMessage(awayTeamName)
//            }else {
//                builder.setMessage(homeTeamName)
//            }
//            builder.setPositiveButton("OK"){dialog, which ->
//                dialog.dismiss()
//            }
//            val dialog: AlertDialog = builder.create()
//            dialog.show()
        }
    }

    private fun updateScoreAdvPoint() {
        if ((homeTeamScoreAdvPoint > awayTeamScoreAdvPoint ) && (homeTeamScoreAdvPoint - awayTeamScoreAdvPoint == 2)) {
            updateScoreSet()
        }else if ((awayTeamScoreAdvPoint > homeTeamScoreAdvPoint) && (awayTeamScoreAdvPoint - homeTeamScoreAdvPoint == 2)) {
            updateScoreSet()
        }
    }

    private fun updateScoreHomeAdvPoint() {
        homeTeamScoreAdvPoint++
        formattedHomeTeamScoreAdvPoint = String.format("%02d", homeTeamScoreAdvPoint)
    }

    private fun updateScoreAwayAdvPoint() {
        awayTeamScoreAdvPoint++
        formattedAwayTeamScoreAdvPoint = String.format("%02d", awayTeamScoreAdvPoint)
    }

}