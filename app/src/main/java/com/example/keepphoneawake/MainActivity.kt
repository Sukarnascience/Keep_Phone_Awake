package com.example.keepphoneawake

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var secs:Int = 0
    private var mins:Int = 0
    private var hrs:Int = 0

    lateinit var mainHandler: Handler

    private val keepupdating = object:Runnable{
        override fun run() {
            timmer()
            mainHandler.postDelayed(this,1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        mainHandler= Handler(Looper.getMainLooper())

    }

    override fun onPause() {
        super.onPause()
        mainHandler.removeCallbacks(keepupdating)
    }

    override fun onResume() {
        super.onResume()
        mainHandler.post(keepupdating)
    }

    private fun timmer(){

        val datain:TextView = findViewById(R.id.textView6)

        secs+=1
        if(secs==60){
            mins+=1
            secs=0
            if(mins==60){
                hrs+=1
                mins=0
            }
        }
        datain.text=("$hrs:$mins:$secs")

    }
}