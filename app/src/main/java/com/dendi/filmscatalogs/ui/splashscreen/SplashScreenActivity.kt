package com.dendi.filmscatalogs.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.ui.home.HomeActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)

        actionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, HomeActivity::class.java))
            finish()
        }, 3000L)
    }
}