package cn.edu.sdut.wwzmyapplication.logic.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Fruit(var name:String, var imageId: Int, var content: String){
    @PrimaryKey(autoGenerate = true)
    var id = 0L
}