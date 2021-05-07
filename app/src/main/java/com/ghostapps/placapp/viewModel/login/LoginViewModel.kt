package com.ghostapps.placapp.viewModel.login

import androidx.lifecycle.MutableLiveData
import com.ghostapps.placapp.domain.useCases.Login
import com.ghostapps.placapp.ui.home.HomeActivity
import com.ghostapps.placapp.viewModel.BaseViewModel

class LoginViewModel(
    private val contract: LoginContract,
    private val login: Login
): BaseViewModel() {

    var email: String = "teste@gmail.com"
    var password: String = "123456"

    val errorMessage = MutableLiveData<String>()

    fun onLoginPressed() {
        login.execute(
            email = email,
            password = password,
            successCallback = {
                contract.navigateTo(HomeActivity::class.java)
            },
            failureCallback = { errorMessage ->
                this.errorMessage.postValue(errorMessage)
            }
        )
    }

}