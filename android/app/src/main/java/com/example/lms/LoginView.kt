package com.example.lms

import retrofit2.Call
import retrofit2.Response


interface LoginView {
    fun onLoginSuccess(code : Int, result : Result)
    fun onLoginFailure()
}