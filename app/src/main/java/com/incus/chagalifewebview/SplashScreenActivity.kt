package com.incus.chagalifewebview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreenActivity : AppCompatActivity() {

    val SplashTimeOut: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            //어떤 액티비티로 넘어 갈지 설정
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SplashTimeOut)
    }
}
