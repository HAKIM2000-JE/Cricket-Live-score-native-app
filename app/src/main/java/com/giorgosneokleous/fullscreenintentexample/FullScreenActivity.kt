/*
 * MIT License
 *
 * Copyright (c) 2020 Giorgos Neokleous
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.giorgosneokleous.fullscreenintentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_full_screen.*
import kotlinx.android.synthetic.main.activity_main.*
import me.relex.circleindicator.CircleIndicator3
import org.json.JSONArray


class FullScreenActivity : AppCompatActivity() {
    var team1List= mutableListOf<String>("Afghanistan"  , "Australia"  , "Canada" , "India" , "Hong Kong")
    var team2List= mutableListOf<String>("Pakistan" , "South Africa" , "Sri Lanka" , "England" , "New Zealand")
    var BattingTeam = mutableListOf<String>("Pakistan" , "South Africa ", "Canada" , "India" , "New Zeeland")
    var BollingTeam = mutableListOf<String>("Afghanistan","Australia" ,"Sri Lanka" ,"England", "Hong Kong" )
    var images1List= mutableListOf<String>("https://api.api-cricket.com/logo/78_afghanistan.png","https://api.api-cricket.com/logo/5_australia.png","https://api.api-cricket.com/logo/250_canada.png", "https://api.api-cricket.com/logo/30_india.png" ,"https://api.api-cricket.com/logo/232_hong-kong.png")
    var images2List = mutableListOf<String>("https://api.api-cricket.com/logo/10_pakistan.png","https://api.api-cricket.com/logo/9_south-africa.png" , "https://api.api-cricket.com/logo/6_sri-lanka.png","https://api.api-cricket.com/logo/34_england.png","https://api.api-cricket.com/logo/29_new-zealand.png")
    var mydata =JSONArray()
    var times = mutableListOf<String>("13:45","12:00" , "9:5","2:00" ,"7:35")
    var status= mutableListOf<String>("29.3: Sandeep Lamichhane to Jaskaran Malhotra, 2 runs, played towards third man." , "28.2: Sushan Bhari to Jaskaran Malhotra, No run, played towards fine leg." ,"27.3: Sandeep Lamichhane to Jaskaran Malhotra, 1 run, played towards point." ,"26.5: Sompal Kami to Jaskaran Malhotra, 1 run, played towards covers." ,"26.1: Sompal Kami to Monank Patel, No run, played towards mid off.")
    var score1  = mutableListOf<String>("175/4", "136" , "177/4" , "191/5" ,"120")
    var score2  = mutableListOf<String>("141", "180/5" , "110" , "98/2" ,"92")
    var runrate= mutableListOf<String>("14.9" ,"None" , "None" ,"8.9" , "None")
    var reqrunrate= mutableListOf<String>("0.3" ,"None" , "None" ,"None" , "None")
    var Tscore1 = mutableListOf<String>("350/1 (65.4)","220/5 ","278/7 (23.2)","213/1 (65.4)","102/4 (30.9)")
    var Tscore2 = mutableListOf<String>("710/2 ","268/1 (13.7) ","278/7 ","185/8 (205.4)","45")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_full_screen)
        turnScreenOnAndKeyguardOff()

        doAsync {
            RunRequest()
            uiThread { toast("Request Performed") }
        }

        postToList()



        view_pager2.adapter = Adapter(team1List, team2List , images1List , images2List , times , BollingTeam , BattingTeam , status , score1 , score2 , runrate , reqrunrate , Tscore1 , Tscore2)

        val indicator: CircleIndicator3 = findViewById<CircleIndicator3>(R.id.indicator)

        indicator.setViewPager(view_pager2)
    }
    private fun RunRequest()
    {
        val cityName:String = "Delhi"
        //Add your preferred location and API key obtained from OpenWeatherMap.org
        val url = "https://api.openweathermap.org/data/2.5/weather?q=delhi,in&units=metric&appid=282e3728fc3bcede60ff4bf241903353"
        val resultJson = URL(url).readText()
        println(resultJson)
        Log.d("Weather Report",resultJson)

        val jsonObj = JSONObject(resultJson)
        val main = jsonObj.getJSONObject("main")
        val temp = main.getString("temp")+"°C"
        val minmaxTemp = main.getString("temp_min")+"°C/"+main.getString("temp_max")+"°C"
        val cityNameText = findViewById<TextView>(R.id.textView2) as TextView
        val tempText = findViewById<TextView>(R.id.textView) as TextView
        val minmaxTempText = findViewById<TextView>(R.id.textView3) as TextView
        cityNameText.text = cityName
        tempText.text = temp
        minmaxTempText.text = minmaxTemp
    }

    override fun onDestroy() {
        super.onDestroy()
        turnScreenOffAndKeyguardOn()
    }

    private fun addToList(team1: String, team2: String , image1 : String ,  image2 : String , time : String , Bolling : String , Batting :String , statu : String  , teamscore1 : String , teamscore2 : String , matchrunrate : String , matchreqrunrate : String , tscore1 :String , tscore2 :String){
        team1List.add(team1)
        team2List.add(team2)
        images1List.add(image1)
        images2List.add(image2)
        times.add(time)
        BollingTeam.add(Bolling)
        BattingTeam.add(Batting)
        status.add(statu)
        score1.add(teamscore1)
        score2.add(teamscore2)
        runrate.add(matchrunrate)
        reqrunrate.add(matchreqrunrate)
        Tscore2.add(tscore2)
        Tscore1.add(tscore1)


    }
    private fun postToList(){

        for ( i in 0..-1){
            addToList(team1List[i] ,team2List[i], "hakim", "hakim" , times[i] , BollingTeam[i] , BattingTeam[i] , status[i] , score1[i] , score2[i] , runrate[i] , reqrunrate[i] , Tscore1[i] , Tscore2[i])
        }
    }


}
