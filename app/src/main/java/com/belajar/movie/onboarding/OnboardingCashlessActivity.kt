package com.belajar.movie.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.belajar.movie.R
import com.belajar.movie.auth.signIn.SignInActivity
import kotlinx.android.synthetic.main.activity_onboarding_cashless.*

class OnboardingCashlessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_cashless)

        btn_mulai.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
    }
}