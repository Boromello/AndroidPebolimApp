package com.ghostapps.placapp.data.records.useCases

import com.ghostapps.placapp.data.records.RecordDatabase
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.GetAllRegister

class GetAllLocalRegister(
    private val database: RecordDatabase
) : GetAllRegister {

    override fun execute(): Array<RecordModel> {
        return database.recordDao().getRecords().map { recordEntity -> recordEntity.toModel() }.toTypedArray()
    }

}