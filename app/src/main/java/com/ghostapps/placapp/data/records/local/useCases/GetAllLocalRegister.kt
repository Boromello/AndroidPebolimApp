package com.ghostapps.placapp.data.records.local.useCases

import com.ghostapps.placapp.data.records.local.RecordDatabase
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.GetAllRegister

class GetAllLocalRegister(
    private val database: RecordDatabase
) : GetAllRegister {

    fun execute(): Array<RecordModel> {
        return  database.recordDao().getRecords().map { recordEntity -> recordEntity.toModel() }.toTypedArray()
    }

    override fun execute(successCallback: (recordList: Array<RecordModel>) -> Unit) {
        TODO("Not yet implemented")
    }

}