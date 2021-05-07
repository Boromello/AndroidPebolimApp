package com.ghostapps.placapp.viewModel.gameRecords

data class RecordsItem (val homeTeamName: String,
                        val homeTeamScore: Int,

                        val awayTeamName: String,
                        val awayTeamScore: Int,

                        val date: Long)