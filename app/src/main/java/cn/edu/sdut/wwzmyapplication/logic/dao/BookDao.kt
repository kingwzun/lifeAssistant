package cn.edu.sdut.wwzmyapplication.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import cn.edu.sdut.wwzmyapplication.logic.model.Book

@Dao
interface BookDao {

    @Insert
    fun insertBook(book: Book): Long

    @Query("select * from Book")
    fun loadAllBooks(): List<Book>

}