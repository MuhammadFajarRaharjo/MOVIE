package com.belajar.movie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.belajar.movie.auth.signIn.SignInActivity
import com.belajar.movie.onboarding.OnboardingNowPlayingActivity
import com.belajar.movie.utils.Preferences

class SplashScreen : AppCompatActivity() {
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        preferences = Preferences(this)

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = when {
                preferences.getValueBool("login") == true -> {
                    Intent(this, MasterActivity::class.java)
                }
                preferences.getValueString("onboarding") == "1" -> {
                    Intent(this, SignInActivity::class.java)
                }
                else -> {
                    Intent(this, OnboardingNowPlayingActivity::class.java)
                }
            }
            startActivity(intent)
            finish()
        }, 3000)
    }
}