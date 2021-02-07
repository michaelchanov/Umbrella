package com.example.umbrella.data

import retrofit2.Call
import retrofit2.http.GET

interface WeatherAPI {

    @GET("http://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=5fc05d7c39b4fe7adeec21be43dad804&units=metric")
    fun getWeather(): Call<App>
}