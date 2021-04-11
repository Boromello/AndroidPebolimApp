package com.ghostapps.placapp.data.records.useCases

import com.ghostapps.placapp.data.records.RecordDatabase
import com.ghostapps.placapp.data.records.RecordEntity
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.DeleteRegister

class DeleteLocalRegister(
    private val database: RecordDatabase
) : DeleteRegister {
    override fun execute(recordModel: RecordModel) {
        database.recordDao().delete(RecordEntity.fromModel(recordModel))
    }
}