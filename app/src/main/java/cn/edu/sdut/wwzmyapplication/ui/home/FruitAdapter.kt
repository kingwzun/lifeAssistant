package cn.edu.sdut.wwzmyapplication.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sdut.wwzmyapplication.R
import cn.edu.sdut.wwzmyapplication.logic.model.Fruit
import cn.edu.sdut.wwzmyapplication.ui.news.NewsContentFragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_news_content.newsContentFrag
import kotlinx.android.synthetic.main.fragment_home.fruitContentFrag
import kotlin.concurrent.thread

class FruitAdapter(val context: Context,  val viewModel:HomeViewModel, val fruitList: List<Fruit>,val isTwoPane:Boolean,val fragment:FruitContentFragment) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    private var deletedFruit: Fruit? = null
    private var deletedPosition: Int = -1
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }
//    创建ViewHolder实例
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)

        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            if (isTwoPane){
                // 如果是双页模式，则刷新Fragment中的内容
                fragment.refresh(fruit.name,fruit.imageId,fruit.content) //刷新NewsContentFragment界面
                println("333333333")
            }
            else{
                // 如果是单页模式，则直接启动Activity
                val intent = Intent(context, FruitActivity::class.java).apply {
                    putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                    putExtra(FruitActivity.FRUIT_CONTENT, fruit.content)
                    putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
                }
                context.startActivity(intent)
                println("3333333334")
            }


        }
    viewHolder.itemView.setOnLongClickListener {
        val popupMenu = PopupMenu(context, view) // 第一个参数是 Context，第二个参数是关联的视图
        popupMenu.menuInflater.inflate(R.menu.long_popu_menu, popupMenu.menu) // 替换成你的菜单资源 ID
        // 设置菜单项的点击监听器
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {

                R.id.menu_delete -> {
                    // 处理删除选项的逻辑
                    val position = viewHolder.adapterPosition
                    val fruit = fruitList[position]
                    // 将删除的水果数据保存到全局变量中
                    deletedFruit = fruit
                    deletedPosition = position
                    // 删除水果
                    thread {
                        viewModel.deleteUser(fruit)

                    }
                    notifyItemRemoved(position)

                    Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                    .setAction("Undo") {
                        // 恢复删除的水果数据到原来的位置
                        if (deletedFruit != null && deletedPosition != -1) {

                            thread {
                                viewModel.insertFruit(fruit)
                            }

                            Toast.makeText(context, "Data restored", Toast.LENGTH_SHORT).show()
                            // 清空保存的删除信息
                            deletedFruit = null
                            deletedPosition = -1
                            notifyItemInserted(deletedPosition)
                        }
                    }.show()

                    true // 表示事件已被消费
                }
                R.id.menu_move -> {
                    // 处理移动选项的逻辑
                    Toast.makeText(parent.context, "you clicked image move", Toast.LENGTH_SHORT).show()
                    true // 表示事件已被消费
                }
                else -> false
            }
        }
        // 显示弹出菜单
        popupMenu.show()
        true // 表示事件已被消费
    }
//        viewHolder.fruitImage.setOnClickListener {
//            val position = viewHolder.adapterPosition
//            val fruit = fruitList[position]
//            Toast.makeText(parent.context, "you clicked image ${fruit.name}", Toast.LENGTH_SHORT).show()
//        }


        return viewHolder
    }
//    onBindViewHolder()方法用于对 RecyclerView子项的数据进行赋值
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]

        holder.fruitName.text = fruit.name
//    Glide在内部做了许多非常复杂的逻辑操作，其中就包括了图片压缩
//    holder.fruitImage.setImageResource(fruit.imageId)
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage)
    }
    override fun getItemCount() = fruitList.size
}