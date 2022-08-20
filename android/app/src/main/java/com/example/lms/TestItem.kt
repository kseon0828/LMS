package com.example.lms

import com.google.gson.annotations.SerializedName

data class TestItem(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("code") val code: Int,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result2?
)
data class Result2(
    @SerializedName("getTaskRes") var getTaskRes: List<Data>,
    @SerializedName("jwt") val jwt: String
)



//class TestItem {
//    @SerializedName("getTaskRes")
//    var getTaskRes: List<Data>? = null
//
//    override fun toString(): String {
//        return "TestItem{getTaskRes=$getTaskRes}"
//    }
//}



