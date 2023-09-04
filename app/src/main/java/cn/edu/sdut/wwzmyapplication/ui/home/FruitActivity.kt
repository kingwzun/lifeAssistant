package cn.edu.sdut.wwzmyapplication.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import cn.edu.sdut.wwzmyapplication.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_fruit.collapsingToolbar
import kotlinx.android.synthetic.main.activity_fruit.fruitContentText
import kotlinx.android.synthetic.main.activity_fruit.fruitImageView
import kotlinx.android.synthetic.main.activity_fruit.toolbar

class FruitActivity : AppCompatActivity() {
    
    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_CONTENT = "fruit_content"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitContent = intent.getStringExtra(FRUIT_CONTENT) ?: ""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title = fruitName
        Glide.with(this).load(fruitImageId).into(fruitImageView)
        fruitContentText.text = fruitContent
//            generateFruitContent(fruitName)

        
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun generateFruitContent(fruitName: String) = fruitName.repeat(500)

}