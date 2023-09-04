package cn.edu.sdut.wwzmyapplication.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import cn.edu.sdut.wwzmyapplication.R
import cn.edu.sdut.wwzmyapplication.MainActivity
import cn.edu.sdut.wwzmyapplication.logic.model.Fruit
import kotlinx.android.synthetic.main.activity_add_fruit.addFruitFab
import kotlinx.android.synthetic.main.activity_add_fruit.fruitContentEdit
import kotlinx.android.synthetic.main.activity_add_fruit.fruitTitleEdit
import kotlin.concurrent.thread

class AddFruitActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_fruit)


        addFruitFab.setOnClickListener {
            val imageId= R.drawable.apple
            val fruitTitle=fruitTitleEdit.text.toString()
            val fruitContent=fruitContentEdit.text.toString()

//        如果账号是admin且密码是123456，就认为登录成功
            if (fruitTitle.isBlank()){
                Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show()
            }else if (fruitContent.isBlank()){
                Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show()
            }
            else {
                var fruit = Fruit(fruitTitle, imageId, fruitContent)
                thread {
                    viewModel.insertFruit(fruit)
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }
}