package com.ghostapps.placapp.viewModel.home

interface HomeContract {
    fun navigateTo(newClass: Class<*>)
    fun closeApp()
}