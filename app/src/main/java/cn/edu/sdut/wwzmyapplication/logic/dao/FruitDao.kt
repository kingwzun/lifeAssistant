package cn.edu.sdut.wwzmyapplication.logic.dao

import androidx.room.*
import cn.edu.sdut.wwzmyapplication.logic.model.Fruit
import cn.edu.sdut.wwzmyapplication.logic.model.User

@Dao
interface FruitDao {
    @Insert
    fun insertFruit(fruit: Fruit): Long
    @Update
    fun updateFruit(newFruit: Fruit)
//    如果想要从数据库中查询数据，或者使用非实体类参数来增删改数据，那么就必须编写SQL语句
    @Query("select * from Fruit")
    fun loadAllFruits(): List<Fruit>
    @Delete
    fun deleteUser(fruit: Fruit)

}