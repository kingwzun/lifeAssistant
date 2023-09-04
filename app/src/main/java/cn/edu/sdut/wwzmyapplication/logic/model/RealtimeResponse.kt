package cn.edu.sdut.wwzmyapplication.logic.model

import com.google.gson.annotations.SerializedName
//获取实时天气信息接口
class RealtimeResponse(val status: String, val result: Result) {

    class Result(val realtime: Realtime)

    class Realtime(val skycon: String,
                   val temperature: Float,
                   @SerializedName("air_quality") val airQuality: AirQuality)

    class AirQuality(val aqi: AQI)

    class AQI(val chn: Float)

}