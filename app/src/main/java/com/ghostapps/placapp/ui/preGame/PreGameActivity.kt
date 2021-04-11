package com.ghostapps.placapp.ui.preGame

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ghostapps.placapp.R
import com.ghostapps.placapp.databinding.ActivityHomeBinding
import com.ghostapps.placapp.databinding.ActivityPreGameBinding
import com.ghostapps.placapp.ui.gameScore.GameScoreActivity
import com.ghostapps.placapp.viewModel.home.HomeViewModel
import com.ghostapps.placapp.viewModel.preGame.PreGameContract
import com.ghostapps.placapp.viewModel.preGame.PreGameViewModel
import kotlinx.android.synthetic.main.activity_pre_game.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PreGameActivity: AppCompatActivity(), PreGameContract {

    private lateinit var binding: ActivityPreGameBinding
    private val viewModel: PreGameViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pre_game)
        binding.viewModel = viewModel

        setSupportActionBar(preGameToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun goToGame() {
        val intent = Intent(this, GameScoreActivity::class.java)
        intent.putExtra(GameScoreActivity.TEAM_HOME_NAME, viewModel.homeTeamName)
        intent.putExtra(GameScoreActivity.TEAM_AWAY_NAME, viewModel.awayTeamName)
        startActivity(intent)
        finish()
    }

}