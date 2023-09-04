package cn.edu.sdut.wwzmyapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.edu.sdut.wwzmyapplication.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_fruit_content.contentFruitContentText
import kotlinx.android.synthetic.main.fragment_fruit_content.contentFruitImageView
import kotlinx.android.synthetic.main.fragment_fruit_content.contentFruitTitleText
import kotlinx.android.synthetic.main.fragment_fruit_content.fruitContentLayout
import kotlinx.android.synthetic.main.news_content_frag.newsContent
import kotlinx.android.synthetic.main.news_content_frag.newsTitle

class FruitContentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fruit_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
        //将fruit的显示
    fun refresh(fruitName: String, fruitImageId: Int?, fruitContent: String) {
        fruitContentLayout.visibility = View.VISIBLE
        contentFruitTitleText.text = fruitName
        Glide.with(this).load(fruitImageId).into(contentFruitImageView)
        contentFruitContentText.text = fruitContent
    }
}