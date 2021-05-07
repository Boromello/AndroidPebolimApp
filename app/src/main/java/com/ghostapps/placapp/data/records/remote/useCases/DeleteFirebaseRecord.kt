package com.ghostapps.placapp.data.records.remote.useCases

import android.util.Log
import com.ghostapps.placapp.data.records.local.RecordDatabase
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.DeleteRegister
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DeleteFirebaseRecord(
    private val database: RecordDatabase
) : DeleteRegister {
    override fun execute(recordModel: RecordModel) {
        val db = Firebase.firestore
        db.collection("scores")
            .document(recordModel.date.toString())
            .delete()
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "Registro excluido com sucesso")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Erro ao excluir", e)
            }
    }
}