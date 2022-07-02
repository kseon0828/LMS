package com.example.lms

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "UserTable")
data class User(
    @SerializedName(value = "email")val email: String,
    @SerializedName(value = "pwd")val pwd: String,
    @SerializedName(value = "name")val name: String,
    @SerializedName(value = "univ")val univ: String,
    @SerializedName(value = "ssn")val ssn: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
