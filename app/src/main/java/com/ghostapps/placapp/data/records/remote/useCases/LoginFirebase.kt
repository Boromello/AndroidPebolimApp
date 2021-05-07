package com.ghostapps.placapp.data.records.remote.useCases

import com.ghostapps.placapp.domain.useCases.Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginFirebase(
    private val firebaseAuth: FirebaseAuth
): Login {

    override fun execute(
        email: String,
        password: String,
        successCallback: () -> Unit,
        failureCallback: (errorMessage: String) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                successCallback()
            }
            .addOnFailureListener {
                if ((it as? FirebaseAuthInvalidUserException)?.errorCode == "ERROR_USER_NOT_FOUND") {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            successCallback()
                        }
                        .addOnFailureListener {
                            failureCallback(
                                it.localizedMessage ?: "Error ao realizar login, tente novamente mais tarde"
                            )
                        }
                } else {
                    failureCallback(
                        it.localizedMessage ?: "Error ao realizar login, tente novamente mais tarde"
                    )
                }
            }
    }
}