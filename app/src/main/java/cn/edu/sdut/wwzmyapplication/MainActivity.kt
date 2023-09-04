package cn.edu.sdut.wwzmyapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import cn.edu.sdut.wwzmyapplication.ui.theme.WwzMyApplicationTheme
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        setupWithNavController(bottomNavigationView, navController)



        setSupportActionBar(toolbar)
        supportActionBar?.let {
//            在ActionBar不为空的情况 下调用setDisplayHomeAsUpEnabled()方法让导航按钮显示出来
            it.setDisplayHomeAsUpEnabled(true)
//            调用 setHomeAsUpIndicator()方法来设置一个导航按钮图标(默认有图标，替换默认图标)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }


//       将Call菜单项设置为默认选中
        navView.setCheckedItem(R.id.navCall)
/*
        当用户点击了任意菜单项时，就会回调到传入的
        Lambda表达式当中，我们可以在这里编写具体的逻辑处理。这里调用了DrawerLayout的
        closeDrawers()方法将滑动菜单关闭，并返回true表示此事件已被处理
 */
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            将滑动菜单展示出来
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.backup -> {
                val intent = Intent(this, FourthActivity::class.java)
                startActivity(intent)
            }
            R.id.delete -> Toast.makeText(this, "You clicked Delete",
                Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings",
                Toast.LENGTH_SHORT).show()
        }
        return true
    }

}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WwzMyApplicationTheme {
        Greeting("Android")
    }
}