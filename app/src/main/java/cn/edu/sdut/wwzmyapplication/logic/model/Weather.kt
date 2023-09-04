package cn.edu.sdut.wwzmyapplication.logic.model

import cn.edu.sdut.wwzmyapplication.logic.model.DailyResponse
import cn.edu.sdut.wwzmyapplication.logic.model.RealtimeResponse

class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)