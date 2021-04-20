package com.ghostapps.placapp.data.records.local.useCases

import com.ghostapps.placapp.data.records.local.RecordDatabase
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