package com.ghostapps.placapp.ui.gameRecords.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.viewModel.gameRecords.RecordsItem
import kotlinx.android.synthetic.main.item_game_record.view.*

class RecordsListViewHolder(itemView: View, private val onDeletePressed: (recordModel: RecordModel) -> Unit): RecyclerView.ViewHolder(itemView) {

    fun bind(record: RecordsItem) {
        itemView.itemGameRecordHomeTeamName.text = record.homeTeamName
        itemView.itemGameRecordHomeTeamScore.text = record.homeTeamScore.toString()

        itemView.itemGameRecordAwayTeamName.text = record.awayTeamName
        itemView.itemGameRecordAwayTeamScore.text = record.awayTeamScore.toString()

        itemView.itemGameRecordDelete.setOnClickListener {
            onDeletePressed(RecordModel(
                homeTeamName = record.homeTeamName,
                homeTeamScore =  record.homeTeamScore,
                awayTeamName = record.awayTeamName,
                awayTeamScore = record.awayTeamScore,
                date = record.date
            ))
        }
    }

}