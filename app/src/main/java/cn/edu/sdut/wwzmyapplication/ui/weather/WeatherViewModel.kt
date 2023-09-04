package cn.edu.sdut.wwzmyapplication.ui.weather

import androidx.lifecycle.*
import cn.edu.sdut.wwzmyapplication.logic.Repository
import cn.edu.sdut.wwzmyapplication.logic.model.Location

class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()
    var locationLng = ""
    var locationLat = ""
    var placeName = ""
    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat)
    }
    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }
}