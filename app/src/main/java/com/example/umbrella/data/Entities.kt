package com.example.umbrella.data

import com.google.gson.annotations.SerializedName


data class App(
    val weathers: Weathers
)
data class Weathers(
    @SerializedName("temp") val temp: Int,
    @SerializedName("max_temp") val maxTemp: Int,
    @SerializedName("the_temp") val theTemp: Int,
    @SerializedName("wind_speed") val windSpeed: Int,
    @SerializedName("air_pressure") val airPressure: Int,
    @SerializedName("humidity") val humidity: Int
)