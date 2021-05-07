package com.ghostapps.placapp.domain.useCases

interface Login {
    fun execute(
        email: String,
        password: String,
        successCallback: () -> Unit,
        failureCallback: (errorMessage: String) -> Unit
    )
}