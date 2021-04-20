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

    val recordsList = MutableLiveData<Array<RecordModel>>()

    fun loadRecords() {
        Thread {
            recordsList.postValue(getAllRegister.execute())
        }.start()
    }

    fun deleteRegister(recordModel: RecordModel) {
        Thread {
            deleteRegister.execute(recordModel)
            loadRecords()
        }.start()
    }

}