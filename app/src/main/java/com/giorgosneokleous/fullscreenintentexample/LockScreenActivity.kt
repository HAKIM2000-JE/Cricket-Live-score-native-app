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

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


import android.util.Log
import android.widget.TextView

class LockScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)
        turnScreenOnAndKeyguardOff()

        doAsync {
            RunRequest()
            uiThread { toast("Request Performed") }
        }
    }

    private fun RunRequest()
    {
        val cityName:String = "Delhi"
        //Add your preferred location and API key obtained from OpenWeatherMap.org
        val url = "https://api.openweathermap.org/data/2.5/weather?q=delhi,in&units=metric&appid=282e3728fc3bcede60ff4bf241903353"
        val resultJson = URL(url).readText()
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
}
