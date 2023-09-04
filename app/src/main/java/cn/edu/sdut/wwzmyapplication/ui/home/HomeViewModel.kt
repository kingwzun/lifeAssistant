package cn.edu.sdut.wwzmyapplication.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.edu.sdut.wwzmyapplication.MyApplication.Companion.context
import cn.edu.sdut.wwzmyapplication.R
import cn.edu.sdut.wwzmyapplication.database.AppDatabase
import cn.edu.sdut.wwzmyapplication.logic.dao.FruitDao
import cn.edu.sdut.wwzmyapplication.logic.dao.FruitDao_Impl
import cn.edu.sdut.wwzmyapplication.logic.model.Fruit
import kotlin.concurrent.thread

class HomeViewModel : ViewModel() {
    val fruitDao = AppDatabase.getDatabase(context).fruitDao()
    var fruitLiveData = MutableLiveData<List<Fruit>>()
    var fruitName=""
    var imageId=""
    var fruitContent=""
    var fruitList= ArrayList<Fruit>()

    fun init(){
        fruitLiveData.postValue(fruitList)
    }
    fun refreshFruits() {
        thread {
            fruitList= loadAllFruits() as ArrayList<Fruit>
            fruitLiveData.postValue(fruitList)
        }
//        fruitLiveData.value=fruitList
    }
    fun insertFruit(fruit: Fruit)= fruitDao.insertFruit(fruit)

    fun updateFruit(newFruit: Fruit)=fruitDao.updateFruit(newFruit)
    fun loadAllFruits()=fruitDao.loadAllFruits()
    fun deleteUser(fruit: Fruit)=fruitDao.deleteUser(fruit)


}