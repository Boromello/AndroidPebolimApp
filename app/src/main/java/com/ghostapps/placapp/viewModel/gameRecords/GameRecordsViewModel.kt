package com.ghostapps.placapp.viewModel.gameRecords

import androidx.lifecycle.MutableLiveData
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.DeleteRegister
import com.ghostapps.placapp.domain.useCases.GetAllRegister
import com.ghostapps.placapp.viewModel.BaseViewModel

class GameRecordsViewModel(
    private val getAllRegister: GetAllRegister,
    private val deleteRegister: DeleteRegister
): BaseViewModel() {

    val recordsList = MutableLiveData<List<RecordsItem>>()

    fun loadRecords() {
        /*Thread {
            val records = getAllRegister.execute()
            records.sortByDescending { it.date }

            recordsList.postValue(records)
        }.start()*/
        Thread {
            getAllRegister.execute (
                successCallback = {
                    val recordsModel = it.flatMap { recordModel ->
                        val recordList = arrayListOf<RecordsItem>()

                        recordList.add(
                            RecordsItem(
                                homeTeamName = recordModel.homeTeamName,
                                homeTeamScore = recordModel.homeTeamScore,
                                awayTeamName = recordModel.awayTeamName,
                                awayTeamScore = recordModel.awayTeamScore,
                                date = recordModel.date
                            )
                        )
                        recordList
                    }
                    recordsList.postValue(recordsModel)
                })
        }.start()

    }



    fun deleteRegister(recordModel: RecordModel) {
        Thread {
            deleteRegister.execute(recordModel)
            loadRecords()
        }.start()
    }

}