package com.ghostapps.placapp.data.records

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ghostapps.placapp.domain.models.RecordModel
@Entity(tableName = "records_database")
class RecordEntity(

    val homeTeamName: String,
    val homeTeamScore: Int,

    val awayTeamName: String,
    val awayTeamScore: Int,

    @PrimaryKey
    val data: Long
) {
    companion object {
        const val TABLE_NAME = "records_database"

        fun fromModel(recordModel: RecordModel): RecordEntity {
            return RecordEntity(
                homeTeamName = recordModel.homeTeamName,
                homeTeamScore = recordModel.homeTeamScore,
                awayTeamName = recordModel.awayTeamName,
                awayTeamScore = recordModel.awayTeamScore,
                data = recordModel.data
            )
        }
    }

    fun toModel(): RecordModel {
        return RecordModel(
            homeTeamName = homeTeamName,
            homeTeamScore = homeTeamScore,
            awayTeamName = awayTeamName,
            awayTeamScore = awayTeamScore,
            data = data
        )
    }
}