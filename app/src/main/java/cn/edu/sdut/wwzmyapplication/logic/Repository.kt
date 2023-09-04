package cn.edu.sdut.wwzmyapplication.logic

import androidx.lifecycle.liveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import cn.edu.sdut.wwzmyapplication.logic.dao.FruitDao
import cn.edu.sdut.wwzmyapplication.logic.model.Place
import cn.edu.sdut.wwzmyapplication.logic.model.Weather
import cn.edu.sdut.wwzmyapplication.logic.network.SunnyWeatherNetwork
import cn.edu.sdut.wwzmyapplication.logic.dao.PlaceDao
import cn.edu.sdut.wwzmyapplication.logic.model.Fruit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

object Repository {
//    构建统一抛出异常函数
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

//    可以自动构建并返回一个LiveData对象，然后在它的代码块中提供一个挂起函数的上下文
    fun searchPlaces(query: String) = fire(Dispatchers.IO) {//所有代码都运行在子线程

            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
//                包装一个异常信息
                Result.failure(RuntimeException("response status is${placeResponse.status}"))
            }

////    emit()方法将包装的结果发射出去
//        emit(result)
    }


    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {

            coroutineScope {
                //    两个请求并发执行：分别在两个async函数中发起网络请求
                val deferredRealtime = async {
                    SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
                }
                val deferredDaily = async {
                    SunnyWeatherNetwork.getDailyWeather(lng, lat)
                }
                val realtimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()
                if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                    val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                    Result.success(weather)
                } else {
                    Result.failure(
                        RuntimeException(
                            "realtime response status is ${realtimeResponse.status}" +
                                    "daily response status is ${dailyResponse.status}"
                        )
                    )
                }
            }
    }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavedPlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()




}