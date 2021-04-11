package com.ghostapps.placapp.domain.useCases

import com.ghostapps.placapp.domain.models.RecordModel

interface GetAllRegister {
    fun execute(): Array<RecordModel>
}