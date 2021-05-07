package com.ghostapps.placapp.data.records.remote.useCases

import android.util.Log
import com.ghostapps.placapp.data.records.local.RecordDatabase
import com.ghostapps.placapp.domain.models.RecordModel
import com.ghostapps.placapp.domain.useCases.InsertRegister
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InsertFirebaseRecord(
    private val database: RecordDatabase
): InsertRegister {
    override fun execute(recordModel: RecordModel){
        val db = Firebase.firestore
        db.collection("scores")
            .document(recordModel.date.toString())
            .set(recordModel)
            .addOnFailureListener {
                it.localizedMessage ?: "Falha ao inserir no Banco de Dados!"
            }
    }
    }