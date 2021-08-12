package com.belajar.movie.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.belajar.movie.R
import com.belajar.movie.auth.signIn.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_now_playing.*

class OnboardingNowPlayingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_now_playing)

        btn_lanjutkan.setOnClickListener {
            val intent = Intent(this, OnboardingPreSaleActivity::class.java)
            startActivity(intent)
        }

        btn_lewati.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}