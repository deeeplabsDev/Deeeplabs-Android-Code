package com.deeeplabs.deeeplabsandroidcode.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("id") var id: Int = 0,
                @SerializedName("name") var name: String = "",
                @SerializedName("email") var email: String = "",
                @SerializedName("role") var role: String = "",
                @SerializedName("password_") var password_: String = "",
                @SerializedName("api_token") var api_token: String = "") {

}