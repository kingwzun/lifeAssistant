package cn.edu.sdut.wwzmyapplication.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cn.edu.sdut.wwzmyapplication.R
import cn.edu.sdut.wwzmyapplication.logic.model.Fruit
import kotlinx.android.synthetic.main.fragment_fruit_list.homeRecyclerView
import kotlinx.android.synthetic.main.fragment_fruit_list.homeSwipeRefresh
import kotlinx.android.synthetic.main.fragment_home.fab
import kotlinx.android.synthetic.main.fragment_home.fruitContentFrag


class FruitListFragment : Fragment() {
    private var isTwoPane = false
    lateinit var fragment : FruitContentFragment
    val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }
    private lateinit var adapter: FruitAdapter
    var fruitList = ArrayList<Fruit>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fruit_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //    由于在Fragment中调用getActivity()方法有可能返回null，所以使用?.操作符来保证代码的安全性。
        isTwoPane = activity?.findViewById<View>(R.id.fruitContentLayout) != null

        var context = requireContext()

//        获取数据
        viewModel.refreshFruits()

        if(isTwoPane){
            //        线性布局
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
//        val layoutManager = GridLayoutManager(context, 2)
            homeRecyclerView.layoutManager = layoutManager
            fragment =fruitContentFrag as FruitContentFragment
            adapter = FruitAdapter(context,viewModel,fruitList,isTwoPane,fragment)
            homeRecyclerView.adapter = adapter
        }
        else{
            val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        val layoutManager = GridLayoutManager(context, 2)
            homeRecyclerView.layoutManager = layoutManager
//            fragment =fruitContentFrag as FruitContentFragment
            fragment= FruitContentFragment()
            adapter = FruitAdapter(context,viewModel,fruitList,isTwoPane,fragment)
            homeRecyclerView.adapter = adapter
        }


        //监听fruitLiveData
        viewModel.fruitLiveData.observe(requireActivity(), Observer { fruits ->
            if (fruits != null) {
                fruitList= viewModel.fruitLiveData.value as ArrayList<Fruit>
                adapter.notifyDataSetChanged(); // 通知适配器数据已更新
                showFruitInfo(fruits)
                println("222222333")
            } else {
                Toast.makeText(context, "无法成功获取水果信息", Toast.LENGTH_SHORT).show()
            }
            homeSwipeRefresh.isRefreshing = false
        })

//        设置刷新
        homeSwipeRefresh.setColorSchemeResources(com.google.android.material.R.color.primary_material_dark)
        homeSwipeRefresh.setOnRefreshListener { refreshFruits(adapter) }




    }
    private fun refreshFruits(adapter: FruitAdapter) {
//        thread {
//            Thread.sleep(2000)
//            requireActivity().runOnUiThread {
        viewModel.refreshFruits()
        adapter.notifyDataSetChanged()
//                homeSwipeRefresh.isRefreshing = true
//            }
//        }
    }

    private fun showFruitInfo(fruits: List<Fruit>) {
//        线性布局
//        val layoutManager1 = LinearLayoutManager(context)
//        layoutManager1.orientation = LinearLayoutManager.HORIZONTAL
//        瀑布流布局
//        val layoutManager2 = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
////        多列布局
//        val layoutManager = GridLayoutManager(context, 2)
////        LayoutManager用于指定RecyclerView的布局方式
//        homeRecyclerView.layoutManager = layoutManager
//        val adapter = FruitAdapter(requireActivity(),viewModel,fruits,isTwoPane,fragment)
//        homeRecyclerView.adapter = adapter

        if(isTwoPane){
            //        线性布局
            val layoutManager = LinearLayoutManager(context)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
//        val layoutManager = GridLayoutManager(context, 2)
            homeRecyclerView.layoutManager = layoutManager
            fragment =fruitContentFrag as FruitContentFragment
            adapter = FruitAdapter(requireActivity(),viewModel,fruitList,isTwoPane,fragment)
            homeRecyclerView.adapter = adapter
        }
        else{
            val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//        val layoutManager = GridLayoutManager(context, 2)
            homeRecyclerView.layoutManager = layoutManager
//            fragment =fruitContentFrag as FruitContentFragment
            fragment =FruitContentFragment()
            adapter = FruitAdapter(requireActivity(),viewModel,fruitList,isTwoPane,fragment)
            homeRecyclerView.adapter = adapter
        }
    }

}