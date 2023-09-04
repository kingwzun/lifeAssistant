package cn.edu.sdut.wwzmyapplication.ui.other

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cn.edu.sdut.wwzmyapplication.R
import cn.edu.sdut.wwzmyapplication.ui.weather.ThirdActivity
import cn.edu.sdut.wwzmyapplication.database.AppDatabase
import cn.edu.sdut.wwzmyapplication.logic.model.Fruit
import cn.edu.sdut.wwzmyapplication.logic.model.User
import kotlinx.android.synthetic.main.fragment_other.addDataBtn
import kotlinx.android.synthetic.main.fragment_other.button
import kotlinx.android.synthetic.main.fragment_other.clearBtn
import kotlinx.android.synthetic.main.fragment_other.deleteDataBtn
import kotlinx.android.synthetic.main.fragment_other.forceOffline
import kotlinx.android.synthetic.main.fragment_other.infoText
import kotlinx.android.synthetic.main.fragment_other.new_btn
import kotlinx.android.synthetic.main.fragment_other.plusOneBtn
import kotlinx.android.synthetic.main.fragment_other.queryDataBtn
import kotlinx.android.synthetic.main.fragment_other.updateDataBtn
import kotlin.concurrent.thread

class OtherFragment : Fragment() , View.OnClickListener{

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       val context = requireContext()
        //   viewModel
//        注意:绝对不可以直接去创建ViewModel的实例，而是一定要通过ViewModelProvider来获取ViewModel的实例
        sp = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)


        button.setOnClickListener(this)
        new_btn.setOnClickListener(this)
        forceOffline.setOnClickListener(this)



        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        clearBtn.setOnClickListener {
            viewModel.clear()
        }
//        observe()方法来观察数据的变化
        viewModel.counter.observe(viewLifecycleOwner, Observer { count ->
            infoText.text = count.toString()
        })

//        val bookDao = AppDatabase.getDatabase(context).bookDao()
//        val book1 =Book("你好",20,"再见")
//        val book2 =Book("你好",20,"再见")
//        queryDataBtn.setOnClickListener {
//            thread {
//                for (book in bookDao.loadAllBooks()) {
//                    Log.d("111MainActivity", book.toString())
//                }
//            }
//        }
        val fruitDao = AppDatabase.getDatabase(context).fruitDao()
        val fruit1 = Fruit("Apple", R.drawable.apple,"AppleAppleApple")
        val fruit2 = Fruit("Banana", R.drawable.banana,"contentcontentcontentcontent")
        queryDataBtn.setOnClickListener {
            thread {
                for (node in fruitDao.loadAllFruits()) {
                    Log.d("111MainActivity", node.toString())
                }
            }
        }

        addDataBtn.setOnClickListener {
            thread {
                fruit1.id = fruitDao.insertFruit(fruit1)
                fruit2.id = fruitDao.insertFruit(fruit2)
            }
        }

        val userDao = AppDatabase.getDatabase(context).userDao()
        println("wrwwe")
        println(userDao)
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)

//        addDataBtn.setOnClickListener {
//            thread {
//                user1.id = userDao.insertUser(user1)
//                user2.id = userDao.insertUser(user2)
//            }
//        }
        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }
        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Hanks")
            }
        }
//        queryDataBtn.setOnClickListener {
//            thread {
//                for (user in userDao.loadAllUsers()) {
//                    Log.d("111MainActivity", user.toString())
//                }
//            }
//        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button -> {
                AlertDialog.Builder(requireActivity()).apply {
                    setTitle("This is Dialog")
                    setMessage("Something important.")
                    setCancelable(false)
                    setPositiveButton("OK") { dialog, which -> }
                    setNegativeButton("Cancel") { dialog, which -> }
                    show()
                }
            }
            R.id.new_btn -> {
                val intent = Intent(requireActivity(), ThirdActivity::class.java)
                startActivity(intent)
//                ThirdActivity.actionStart(this)
            }
            R.id.forceOffline ->{
                val intent = Intent("cn.edu.sdut.broadcastbestpractice.FORCE_OFFLINE")
                requireActivity().sendBroadcast(intent)
            }
        }
    }

}