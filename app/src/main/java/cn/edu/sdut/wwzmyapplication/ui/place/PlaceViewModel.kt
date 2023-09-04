package cn.edu.sdut.wwzmyapplication.ui.place
//ViewModel的一个重要作用就是可以帮助Activity分担一部分工作，它是专门用于存放与界面相关的数据的。
// 也就是说，只要是界面上能看得到的数据，它的相关变量都应该存放在 ViewModel中
import androidx.lifecycle.*
import cn.edu.sdut.wwzmyapplication.logic.Repository
import cn.edu.sdut.wwzmyapplication.logic.model.Place

    class PlaceViewModel : ViewModel() {
    private val searchLiveData = MutableLiveData<String>()
    val placeList = ArrayList<Place>()
    //如果search变化，就搜索
    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }
    //给search赋值
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }
//    没有开启线程，因此也不必借助LiveData对象来观察数据变 化，直接调用仓库层中相应的接口并返回即可。
    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavedPlace() = Repository.getSavedPlace()
    fun isPlaceSaved() = Repository.isPlaceSaved()
}