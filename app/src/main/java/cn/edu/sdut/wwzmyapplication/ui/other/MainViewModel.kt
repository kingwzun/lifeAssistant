package cn.edu.sdut.wwzmyapplication.ui.other

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(countReserved: Int) : ViewModel() {
//    LiveData可以包含任何类型的数据，并在数据发生变化的时候通知给观察者。
    var counter = MutableLiveData<Int>()


    init {
        counter.value = countReserved
    }
    fun plusOne() {
        val count = counter.value ?: 0
        counter.value = count + 1
    }
    fun clear() {
        counter.value = 0
    }

}