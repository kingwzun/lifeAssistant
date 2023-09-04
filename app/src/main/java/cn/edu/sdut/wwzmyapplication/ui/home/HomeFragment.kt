package cn.edu.sdut.wwzmyapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cn.edu.sdut.wwzmyapplication.MainActivity
import cn.edu.sdut.wwzmyapplication.MyApplication.Companion.context
import cn.edu.sdut.wwzmyapplication.R
import kotlinx.android.synthetic.main.fragment_home.fab
import kotlinx.android.synthetic.main.fragment_home.fruitContentFrag


class HomeFragment : Fragment() , View.OnClickListener{

    companion object {
        fun actionStart(fruitName: String, fruitImageId:Int, fruitContent: String)  {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra("fruitName", fruitName)
                putExtra("fruitImageId", fruitImageId)
                putExtra("fruitContent", fruitContent)
            }
            context.startActivity(intent)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val fruitName = activity?.intent?.getStringExtra("fruitName")
        val fruitImageId = activity?.intent?.getIntExtra("fruitImageId",R.drawable.apple)
        val fruitContent = activity?.intent?.getStringExtra("fruitContent")
        if (fruitName != null && fruitContent != null && fruitImageId!=null) {
            var fruitFragment=fruitContentFrag as FruitContentFragment
            fruitFragment.refresh(fruitName,fruitImageId,fruitContent)
        }

        //设置fab监听器
        fab.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fab ->{
                val intent = Intent(requireContext(), AddFruitActivity::class.java)
                startActivity(intent)
            }
        }
    }
}