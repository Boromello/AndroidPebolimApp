package com.ghostapps.placapp.data.records

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ghostapps.placapp.domain.models.RecordModel
import com.google.gson.annotations.SerializedName

@Entity(tableName = RecordEntity.TABLE_NAME)
class RecordEntity(

    @SerializedName("team_a_name")
    val homeTeamName: String,
    @SerializedName("team_a_score")
    val homeTeamScore: Int,

    @SerializedName("team_b_name")
    val awayTeamName: String,
    @SerializedName("team_b_score")
    val awayTeamScore: Int,

    @PrimaryKey
    @SerializedName("timestamp")
    val date: Long
) {
    companion object {
        const val TABLE_NAME = "records_database"

        fun fromModel(recordModel: RecordModel): RecordEntity {
            return RecordEntity(
                homeTeamName = recordModel.homeTeamName,
                homeTeamScore = recordModel.homeTeamScore,
                awayTeamName = recordModel.awayTeamName,
                awayTeamScore = recordModel.awayTeamScore,
                date = recordModel.date
            )
        }
    }

    fun toModel(): RecordModel {
        return RecordModel(
            homeTeamName = homeTeamName,
            homeTeamScore = homeTeamScore,
            awayTeamName = awayTeamName,
            awayTeamScore = awayTeamScore,
            date = date
        )
    }
}