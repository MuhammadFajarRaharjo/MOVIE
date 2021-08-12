package com.belajar.movie.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.belajar.movie.R
import kotlinx.android.synthetic.main.activity_onboarding_pre_sale.*

class OnboardingPreSaleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding_pre_sale)

        btn_lanjutkan.setOnClickListener {
            val intent = Intent(this, OnboardingCashlessActivity::class.java)
            startActivity(intent)
        }
    }
}