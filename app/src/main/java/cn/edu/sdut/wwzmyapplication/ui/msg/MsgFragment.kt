package cn.edu.sdut.wwzmyapplication.ui.msg


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import cn.edu.sdut.wwzmyapplication.R

import cn.edu.sdut.wwzmyapplication.logic.model.Msg
import kotlinx.android.synthetic.main.fragment_msg.msgInputText
import kotlinx.android.synthetic.main.fragment_msg.msgRecyclerView
import kotlinx.android.synthetic.main.fragment_msg.msgSend




class MsgFragment : Fragment() , View.OnClickListener {

     var msgList = ArrayList<Msg>()
    private lateinit var adapter:MsgAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_msg, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        设置发送按钮
        msgSend.setOnClickListener(this)

//        设置消息
        initMsg()
        val layoutManager =LinearLayoutManager(activity)
        msgRecyclerView.layoutManager = layoutManager
        adapter = MsgAdapter(requireContext(),msgList)
        msgRecyclerView.adapter = adapter


    }
        override fun onClick(v: View?) {
        when (v) {
            msgSend -> {
                val content = msgInputText.text.toString()
                println("3433")
                println(content)
                if (content.isNotEmpty()) {
                    val msg = Msg(content, Msg.TYPE_SENT)
                    msgList.add(msg)
                    adapter?.notifyItemInserted(msgList.size - 1) // 当有新消息时， 刷新RecyclerView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size - 1) // 将RecyclerView 定位到最后一行
                    msgInputText.setText("") // 清空输入框中的内容
                }
            }
        }
    }
    private fun initMsg() {
        val msg1 = Msg("Hello guy.", Msg.TYPE_RECEIVED)
        msgList.add(msg1)
        val msg2 = Msg("Hello. Who is that?", Msg.TYPE_SENT)
        msgList.add(msg2)
        val msg3 = Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED)
        msgList.add(msg3)

    }


}