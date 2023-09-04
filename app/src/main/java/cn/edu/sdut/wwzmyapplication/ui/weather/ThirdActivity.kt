package cn.edu.sdut.wwzmyapplication.ui.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.edu.sdut.wwzmyapplication.R

class ThirdActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, ThirdActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.third_layout)
    }
}