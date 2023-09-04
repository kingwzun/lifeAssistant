package cn.edu.sdut.wwzmyapplication.ui.msg

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.edu.sdut.wwzmyapplication.logic.model.Fruit
import cn.edu.sdut.wwzmyapplication.logic.model.Msg


class MsgViewModel(msgs: ArrayList<Msg>) : ViewModel() {
    //    LiveData可以包含任何类型的数据，并在数据发生变化的时候通知给观察者。
    var msgList = MutableLiveData<ArrayList<Msg>>()


    init {
        msgList.value = msgs
    }
    fun pushOne(msg : Msg) {
        msgList.value?.add(msg)
    }
    fun clear() {
        msgList=MutableLiveData<ArrayList<Msg>>()
    }

}