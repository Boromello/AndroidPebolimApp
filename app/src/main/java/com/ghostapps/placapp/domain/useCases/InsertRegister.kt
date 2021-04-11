package com.ghostapps.placapp.domain.useCases

import com.ghostapps.placapp.domain.models.RecordModel

interface InsertRegister {
    fun execute(recordModel: RecordModel)
}