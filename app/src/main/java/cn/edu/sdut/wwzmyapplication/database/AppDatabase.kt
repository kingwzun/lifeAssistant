package cn.edu.sdut.wwzmyapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cn.edu.sdut.wwzmyapplication.logic.dao.BookDao
import cn.edu.sdut.wwzmyapplication.logic.dao.FruitDao
import cn.edu.sdut.wwzmyapplication.logic.dao.UserDao
import cn.edu.sdut.wwzmyapplication.logic.model.Book
import cn.edu.sdut.wwzmyapplication.logic.model.Fruit
import cn.edu.sdut.wwzmyapplication.logic.model.User

@Database(version = 1, entities = [User::class, Book::class, Fruit::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao
    abstract fun fruitDao(): FruitDao
    companion object {

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "app_database")
                .build().apply {
                instance = this
            }
        }
    }

}