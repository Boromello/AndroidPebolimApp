package com.ghostapps.placapp.data.records.remote.useCases

import com.ghostapps.placapp.data.records.RecordEntity
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.GetAllRegister
import com.ghostapps.placapp.infra.http.HttpAdapter
import com.google.gson.*

class GetAllRemoteRegister(
    private val httpAdapter: HttpAdapter
): GetAllRegister {
    override fun execute(): Array<RecordModel> {
        val bodyResponse = httpAdapter.get("https://run.mocky.io/v3/fa459eee-25d1-4e02-a307-df46ca88be5d")
        val recordEntities = Gson().fromJson(bodyResponse, Array<RecordEntity>::class.java)

        return recordEntities.map { recordEntity -> recordEntity.toModel() }.toTypedArray()
    }
}