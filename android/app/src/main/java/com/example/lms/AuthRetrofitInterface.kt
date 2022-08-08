package com.example.lms

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthRetrofitInterface {
    @POST("/users")
    fun signUp(@Body user: User): Call<AuthResponse>

    @POST("/auth/login")
    fun login(@Body user: User): Call<AuthResponse>

    @GET("/auth/jwt")
    fun autologin(@Body user: User): Call<AuthResponse>
}