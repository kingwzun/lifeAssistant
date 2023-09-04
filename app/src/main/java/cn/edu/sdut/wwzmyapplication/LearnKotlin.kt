package cn.edu.sdut.wwzmyapplication

import kotlin.math.max

class LearnKotlin{
fun largerNumber2(num1:Int ,num2 : Int ): Int {
    return max(num1,num2)
}
fun largerNumber1(num1:Int ,num2:Int )=max(num1,num2)

fun largerNumber(num1:Int ,num2:Int)= if(num1>num2){ num1 } else  num2

fun getName(name : String)=when(name){
    "Tom" ->86
    "Boot" ->77
    else ->0
}

fun main(){
    val a=10
    var b=10
    b=100
    print(largerNumber(a,b))

    val range  =0..10

    for(i  in  0..10){
        println(i)
    }

}

}