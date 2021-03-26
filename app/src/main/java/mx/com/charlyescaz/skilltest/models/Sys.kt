package mx.com.charlyescaz.skilltest.models

import com.google.gson.annotations.SerializedName

class Sys(
        val type: Int = 0,
        @SerializedName("id")
        val sysId: Int = 0,
        val country: String = "",
        val sunrise: Int = 0,
        val sunset: Int = 0
)