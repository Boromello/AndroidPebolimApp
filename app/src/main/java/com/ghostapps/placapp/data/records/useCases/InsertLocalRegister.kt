package com.ghostapps.placapp.data.records.useCases

import com.ghostapps.placapp.data.records.RecordDatabase
import com.ghostapps.placapp.data.records.RecordEntity
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.InsertRegister

class InsertLocalRegister(
    private val database: RecordDatabase
): InsertRegister {
    override fun execute(recordModel: RecordModel) {
        database.recordDao().insert(RecordEntity.fromModel(recordModel))
    }
}