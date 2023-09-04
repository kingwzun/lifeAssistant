package cn.edu.sdut.wwzmyapplication.logic.network

import cn.edu.sdut.wwzmyapplication.MyApplication
import cn.edu.sdut.wwzmyapplication.logic.model.DailyResponse
import cn.edu.sdut.wwzmyapplication.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>

    @GET("v2.5/${MyApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

}