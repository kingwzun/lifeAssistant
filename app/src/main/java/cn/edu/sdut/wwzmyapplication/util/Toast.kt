package cn.edu.sdut.wwzmyapplication.util

import android.widget.Toast
import cn.edu.sdut.wwzmyapplication.MyApplication

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}