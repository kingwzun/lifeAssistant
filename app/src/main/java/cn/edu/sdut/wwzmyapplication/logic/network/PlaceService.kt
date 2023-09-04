package cn.edu.sdut.wwzmyapplication.logic.network

import cn.edu.sdut.wwzmyapplication.MyApplication
import cn.edu.sdut.wwzmyapplication.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${MyApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

}