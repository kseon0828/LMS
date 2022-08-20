package com.example.lms

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import java.util.*

interface TaskRetrofitAPI {
    @GET("/task/20220705")
    fun getData(
        @Header("x-access-token") jwt: String?
    ): Call<TestItem>





//    fun getData(
//        @Path("date") date: Date): Call<TestItem>


    //@Query("taskName") taskName:String
}

