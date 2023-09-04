package cn.edu.sdut.wwzmyapplication.logic.dao

import androidx.room.*
import cn.edu.sdut.wwzmyapplication.logic.model.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User): Long
    @Update
    fun updateUser(newUser: User)

//    如果想要从数据库中查询数据，或者使用非实体类参数来增删改数据，那么就必须编写SQL语句
    @Query("select * from User")
    fun loadAllUsers(): List<User>
//查询所有年龄大于指定参数的用户
    @Query("select * from User where age > :age")
    fun loadUsersOlderThan(age: Int): List<User>

    @Delete
    fun deleteUser(user: User)
//**********    使用非实体类参数来增删改数据，，都要使用@Query注解才行**************//
    @Query("delete from User where lastName = :lastName")
    fun deleteUserByLastName(lastName: String): Int

}